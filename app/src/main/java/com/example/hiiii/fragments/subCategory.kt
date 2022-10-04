package com.example.hiiii.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hiiii.R
import com.example.hiiii.data.Sub_users
import com.example.hiiii.databinding.FragmentSubCategoryBinding


class subCategory : Fragment() {

    private var _binding : FragmentSubCategoryBinding? = null
    private val binding get() = _binding!!
    val sub_professions = ArrayList<Sub_users>()


    val  displayList = ArrayList<Sub_users>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentSubCategoryBinding.inflate(inflater, container, false)


        return binding.root
    }


}