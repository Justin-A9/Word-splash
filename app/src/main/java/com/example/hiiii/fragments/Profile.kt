package com.example.hiiii.fragments

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.hiiii.R
import com.example.hiiii.databinding.FragmentProfileBinding
import com.example.hiiii.datasource.PreferenceManager


class Profile : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentProfileBinding
    var preferenceManager: PreferenceManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        preferenceManager?.preferenceManager(requireContext())

        navController = NavHostFragment.findNavController(this)

        binding.backBtn.setOnClickListener {
            navController.navigate(R.id.action_profile_to_menuFragment)
        }
        binding.editProfileBTN.setOnClickListener {
            navController.navigate(R.id.action_profile_to_edit_Profile)
        }

        val args = this.arguments
        val getData1 = args?.get("email")
        val getData2 = args?.get("tellUs")
        val getData3 = args?.get("username")

        val currentImage = args?.getString("profileImage")

        if (currentImage != null) {
            val fileUri = Uri.parse(currentImage)
            binding.imageProfile.setImageURI(fileUri)
        }


        /*val bitmap = BitmapFactory.decodeFile(imagePath.toString())
        binding.imageProfile.setImageBitmap(bitmap)
        val myUri = Uri.parse(imagePath.toString())
        binding.imageProfile.setImageURI(myUri)
        if (image != null) {
            binding.imageProfile.setImageResource(image)
        }

         */


        binding.about.text = getData2.toString()
        binding.profileEmail.text = getData1.toString()
        binding.profileName.text = getData3.toString()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

}