package com.example.hiiii

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hiiii.databinding.FragmentForgotPasswordFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class Forgot_password_fragment : Fragment() {

    private var _binding : FragmentForgotPasswordFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForgotPasswordFragmentBinding.inflate(inflater, container, false)

        val view = binding.root

        auth = Firebase.auth

        binding.submit.setOnClickListener {

            val forgot_password_email = binding.forgotPasswordEmail.text.toString().trim { it <= ' '}

            if (forgot_password_email.isEmpty()){
                Toast.makeText(requireContext(), "Email cannot be left blank", Toast.LENGTH_SHORT).show()
            }else{

                FirebaseAuth.getInstance().sendPasswordResetEmail(forgot_password_email)
                    .addOnCompleteListener{task ->
                        if (task.isSuccessful){
                            Toast.makeText(requireContext(), "Email sent", Toast.LENGTH_SHORT).show()

                        }else{
                            Toast.makeText(requireContext(), task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                        }

                    }
            }
        }

        return view
    }

}