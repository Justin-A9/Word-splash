package com.example.squash.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.squash.R
import com.example.squash.databinding.FragmentDropAFeedBackBinding


class DropAFeedBack : Fragment() {

    private lateinit var binding: FragmentDropAFeedBackBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentDropAFeedBackBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)

        binding.backBtn.setOnClickListener {
            navController.navigate(R.id.action_dropAFeedBack_to_menuFragment)
        }

        binding.submit.setOnClickListener {

            if (binding.dropFeedback.text.toString().isEmpty()){
                Toast.makeText(requireContext(), "Please enter your text", Toast.LENGTH_SHORT).show()
            }else if (binding.dropFeedback.text.toString().length < 10){
                Toast.makeText(requireContext(), "please input a longer text", Toast.LENGTH_SHORT).show()
            }else{
                navController.navigate(R.id.action_dropAFeedBack_to_menuFragment)
                Toast.makeText(requireContext(), "Thank you, We have received your feedback", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }


}