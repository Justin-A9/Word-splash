package com.example.hiiii.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.hiiii.R
import com.example.hiiii.databinding.FragmentPrivacyPolicyBinding


class privacy_policy : Fragment() {


    private lateinit var binding:FragmentPrivacyPolicyBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrivacyPolicyBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)

        binding.baseline.setOnClickListener {
            navController.navigate(R.id.action_privacy_policy_to_menuFragment)
        }

        return binding.root
    }


}