package com.example.hiiii.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hiiii.Adapters.Adapter
import com.example.hiiii.Activities.SubCategory
import com.example.hiiii.data.Occupations
import com.example.hiiii.databinding.FragmentHomeBinding
import java.util.Timer


class homeFragment : Fragment() {

    private lateinit var timer: Timer
    private var _binding :FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root


        timer = Timer()
        //navController = NavHostFragment.findNavController(this)
        var recycler = binding.recycler
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