package com.example.hiiii.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.textclassifier.TextClassifierEvent.LanguageDetectionEvent
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.hiiii.Activities.LandingPage
import com.example.hiiii.R
import com.google.firebase.auth.FirebaseAuth

class SplashScreenFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        navController = NavHostFragment.findNavController(this)

        auth = FirebaseAuth.getInstance()
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)

        val splashScreenImage = view.findViewById<ImageView>(R.id.splashScreenImage)
            splashScreenImage.alpha = 0f

            splashScreenImage.animate().setDuration(1500).alpha(1f).withEndAction {
                CheckLoggedInState()
                activity?.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }

        return view
    }

    private fun CheckLoggedInState(){
        if (auth.currentUser == null){
            navController.navigate(R.id.action_splashScreenFragment_to_onBoardingScreen)
        }else{
            val intent = Intent(requireContext(), LandingPage::class.java)
            activity?.startActivity(intent)
        }
    }

}