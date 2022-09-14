package com.example.hiiii.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hiiii.R
import com.example.hiiii.databinding.FragmentHomeBinding


class homeFragment : Fragment() {

    private var _binding :FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

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

        recycler.adapter = adapter
        return view
    }

}