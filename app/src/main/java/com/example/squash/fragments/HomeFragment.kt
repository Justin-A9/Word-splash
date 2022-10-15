package com.example.squash.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.squash.R
import com.example.squash.activities.SubCategory
import com.example.squash.adapters.Adapter
import com.example.squash.data.Occupations
import com.example.squash.databinding.FragmentHomeBinding
import com.example.squash.datasource.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var db: FirebaseFirestore? = null
    private var userId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        val currentUser = FirebaseAuth.getInstance().currentUser
        db = FirebaseFirestore.getInstance()

        if (currentUser != null) {
            userId = currentUser.uid
        }
        val docRef = db?.collection("users")?.document(userId!!)

        docRef?.get()?.addOnSuccessListener { documentSnapshot ->
            val user = documentSnapshot.toObject(Users::class.java)

            binding.welcome.text = getString(R.string.welcome, user?.username)
        }
            val recycler = binding.recycler
            recycler.layoutManager = GridLayoutManager(requireContext(), 2)



            val occupations = ArrayList<Occupations>()
            occupations.add(Occupations("Medicine"))
            occupations.add(Occupations("Sport"))
            occupations.add(Occupations("Finance"))
            occupations.add(Occupations("Food"))
            occupations.add(Occupations("Education"))
            occupations.add(Occupations("Countries"))
            val adapter = Adapter(occupations)

            adapter.onItemClick = {
                val intent = Intent(requireContext(), SubCategory::class.java)
                intent.putExtra("name", it.name)
                startActivity(intent)
            }
            recycler.adapter = adapter



        return binding.root
    }
}