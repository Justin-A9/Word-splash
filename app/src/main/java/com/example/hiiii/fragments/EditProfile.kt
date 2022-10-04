package com.example.hiiii.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.Navigation
import com.example.hiiii.R
import com.example.hiiii.databinding.Fragment2Binding
import com.google.android.material.textfield.TextInputEditText
import de.hdodenhof.circleimageview.CircleImageView
import java.io.IOException


class Edit_Profile : Fragment() {

    var imageUri: Uri? = null
    val requestCode = 0
    private var _binding :Fragment2Binding? = null
    private val binding = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view = binding.root

        _binding = Fragment2Binding.inflate(inflater, container, false)
        val btn = binding.saveChanges


        btn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fragment2_to_fragment1)
        }

        val imageProfile = view.findViewById<CircleImageView>(R.id.imageProfile)

        imageProfile.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivityForResult(intent, 0)

            val btn = view.findViewById<AppCompatButton>(R.id.saveChanges)

            btn.setOnClickListener {

                val bundle =Bundle()
                val email = binding.editProfEmail.text.toString()

                bundle.putString("email", email)

                val username = binding.username.text.toString()
                bundle.putString("username", username)


                val tellUs = binding.tellUs.text.toString()
                bundle.putString("tellUs", tellUs)
                Navigation.findNavController(view).navigate(R.id.action_fragment2_to_fragment1, bundle)
            }
        }

        return view
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null){
            val imageProfile = view?.findViewById<CircleImageView>(R.id.imageProfile)

            imageUri = data.data
            val bitmap = imageUri?.let { uriToBitMap(it) }
            imageProfile?.setImageBitmap(bitmap)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding!= null
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