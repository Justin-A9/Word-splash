package com.example.hiiii.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.hiiii.R
import com.example.hiiii.databinding.FragmentMenuBinding

class menuFragment : Fragment() {


    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.linearProfile.setOnClickListener {
            navController.navigate(R.id.action_menuFragment_to_profile)
        }
        binding.linearNotifications.setOnClickListener {
            navController.navigate(R.id.action_menuFragment_to_notifications)
        }
        binding.linearPrivacyPolicy.setOnClickListener {
            navController.navigate(R.id.action_menuFragment_to_privacy_policy)
        }

        binding.linearDropFeedback.setOnClickListener {

            navController.navigate(R.id.action_menuFragment_to_dropAFeedBack)

        }
        binding.linearShare.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "type/palin"
            val shareBody = "You are body"
            val shareSub = "You subject here"
            intent.putExtra(Intent.EXTRA_SUBJECT, shareBody)
            intent.putExtra(Intent.EXTRA_TEXT, shareSub)
            startActivity(Intent.createChooser(intent, "Share your app"))
        }
    }

}