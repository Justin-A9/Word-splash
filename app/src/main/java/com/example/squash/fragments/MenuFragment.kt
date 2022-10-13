package com.example.squash.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.squash.R
import com.example.squash.databinding.FragmentMenuBinding
import com.google.firebase.auth.FirebaseAuth

class MenuFragment : Fragment() {


    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()
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

        binding.linearLogin.setOnClickListener {
            auth.signOut()
            navController.navigate(R.id.action_menuFragment_to_login_page_fragment2)
        }
        binding.linearShare.setOnClickListener {

            val intent= Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"Hey Check out this Great app:")
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent,"Share To:"))
        }
    }

}