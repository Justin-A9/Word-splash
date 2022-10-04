package com.example.hiiii.fragments

import android.R.attr.data
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.hiiii.R
import com.example.hiiii.databinding.FragmentEditProfileBinding
import com.example.hiiii.datasource.PreferenceManager
import de.hdodenhof.circleimageview.CircleImageView
import java.io.IOException


class EditProfile : Fragment() {

    private lateinit var navController: NavController
    private  var imageUri: Uri? = null

    val requestCode = 0
    private lateinit var binding: FragmentEditProfileBinding
    private var selectedImageUri: Uri? = null
    var preferenceManager: PreferenceManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        preferenceManager?.preferenceManager(requireContext())

        navController = NavHostFragment.findNavController(this)



        binding.saveChanges.setOnClickListener {

        }

        binding.backBtn.setOnClickListener {
            navController.navigate(R.id.action_edit_Profile_to_profile)
        }

        binding.imageProfile.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivityForResult(intent, 0)

            binding.saveChanges.setOnClickListener {

                val bundle = Bundle()
                val email = binding.editProfEmail.text.toString()


                bundle.putString("email", email)
                bundle.putString("image", binding.imageProfile.toString())

                val username = binding.username.text.toString()
                bundle.putString("username", username)

                bundle.putString("profileImage", imageUri.toString())



                val tellUs = binding.tellUs.text.toString()
                bundle.putString("tellUs", tellUs)
                navController.navigate(R.id.action_edit_Profile_to_profile, bundle)
            }
        }

        return binding.root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            val imageProfile = view?.findViewById<CircleImageView>(R.id.imageProfile)

            imageUri = data?.data!!
            preferenceManager?.putString("imagesent", imageUri.toString())
            val bitmap = imageUri?.let { uriToBitMap(it) }
            imageProfile?.setImageBitmap(bitmap)
        }
    }

    private fun uriToBitMap(selectedFileUri: Uri): Bitmap? {

        try {
            val parcelFileDescriptor =
                context?.contentResolver?.openFileDescriptor(selectedFileUri, "r")
            val fileDescriptor = parcelFileDescriptor!!.fileDescriptor
            return BitmapFactory.decodeFileDescriptor(fileDescriptor)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }


}