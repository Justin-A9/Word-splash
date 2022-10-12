package com.example.squash.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.squash.data.SubUsers
import com.example.squash.databinding.FragmentSubCategoryBinding


class SubCategory : Fragment() {

    private var _binding : FragmentSubCategoryBinding? = null
    private val binding get() = _binding!!
    val sub_professions = ArrayList<SubUsers>()


    val  displayList = ArrayList<SubUsers>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentSubCategoryBinding.inflate(inflater, container, false)


        return binding.root
    }


}