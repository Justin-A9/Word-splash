package com.example.hiiii.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.hiiii.R

class SplashScreenFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)

        val splashScreenImage = view.findViewById<ImageView>(R.id.splashScreenImage)
            splashScreenImage.alpha = 0f

            splashScreenImage.animate().setDuration(1500).alpha(1f).withEndAction {

                findNavController().navigate(R.id.action_splashScreenFragment_to_onBoardingScreen)

                activity?.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }

        return view
    }


}