package com.example.squash.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.squash.adapters.Adapter
import com.example.squash.activities.SubCategory
import com.example.squash.data.Occupations
import com.example.squash.databinding.FragmentHomeBinding
import com.example.squash.datasource.Constants
import com.example.squash.datasource.FireStoreData
import com.google.firebase.auth.FirebaseAuth
import java.util.Timer


class HomeFragment : Fragment() {

    private lateinit var timer: Timer
    private lateinit var  binding :FragmentHomeBinding
    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        auth = FirebaseAuth.getInstance()


        timer = Timer()
        navController = NavHostFragment.findNavController(this)
        var recycler = binding.recycler
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)


        val args = this.arguments
        val data = auth.currentUser?.displayName
        binding.welcome.text = "Welcome, $data"

        val occupations = ArrayList<Occupations>()
        occupations.add(Occupations("Medicine"))
        occupations.add(Occupations("Sport"))
        occupations.add(Occupations("Finance"))
        occupations.add(Occupations("Food"))
        occupations.add(Occupations("Education"))
        occupations.add(Occupations("Countries"))
        val adapter = Adapter(occupations)

        adapter.onItemClick = {
            val bundle = Bundle()
            val intent = Intent(requireContext(), SubCategory::class.java)
            intent.putExtra("name", it.name)
            startActivity(intent)
//            bundle.putString("name", it.name)
//            navController.navigate(R.id.action_homeFragment2_to_sub_categories, bundle)
        }
        recycler.adapter = adapter
        return view
    }


}