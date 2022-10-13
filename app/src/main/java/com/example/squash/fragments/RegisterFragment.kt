package com.example.squash.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.squash.R
import com.example.squash.databinding.FragmentRegisterFragmentBinding
import com.example.squash.datasource.FireStoreData
import com.example.squash.datasource.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.HashMap

class RegisterFragment : Fragment() {

    private var _binding : FragmentRegisterFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var fStore: FirebaseFirestore
    private lateinit var userID : String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentRegisterFragmentBinding.inflate(inflater, container, false)

        val view = binding.root

        auth = Firebase.auth
        fStore = FirebaseFirestore.getInstance()

        binding.iAlreadyHaveAnAccount.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.action_register_fragment_to_login_page_fragment)
        }
        binding.createAccount.setOnClickListener {
            registerUser()
            binding.progressBar.visibility = View.VISIBLE
        }

        binding.loginTextview.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.action_register_fragment_to_login_page_fragment)
        }

        return view
    }

    private fun registerUser(){

        var email = binding.registerEmail.text.toString().trim { it <= ' ' }
        var password = binding.registerPassword.text.toString().trim { it <= ' ' }
        var confirmPassword = binding.registerConfirmPassword.text.toString().trim { it <= ' ' }
        var username = binding.userName.text.toString().trim()



        if (password != confirmPassword) {
            Toast("Password and confirm password Mis-match")
            binding.progressBar.visibility = View.GONE
        }else if (password.length < 6){
            Toast("Password must be more than 6 characters")
            binding.progressBar.visibility = View.GONE
        }else if(email.isEmpty() || password.isEmpty()){
            Toast("Please fill in all the required information")
            binding.progressBar.visibility = View.GONE
        }else{
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {

                        Toast("You have successfully created a account")
                        val user = FirebaseAuth.getInstance().currentUser?.uid
                        val myUser = Users(
                            binding.registerEmail.text.toString().trim { it <= ' ' },
                            binding.registerPassword.text.toString().trim { it <= ' ' },
                            binding.registerConfirmPassword.text.toString().trim { it <= ' ' },
                            binding.userName.text.toString().trim()
                        )
                        if (user != null) {
                            fStore.collection("users").document(user).set(myUser)
                        }
                        binding.progressBar.visibility = View.GONE
                        successAlertDialog()


                    } else {

                        // If sign in fails, display a message to the user.
                        Toast(task.exception?.message.toString())
                        failedAlertDialog()
                        binding.progressBar.visibility = View.GONE
                    }
                }
        }
    }

    private fun successAlertDialog(){
        val v = View.inflate(requireContext(), R.layout.success_modal_dialog, null)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(v)
        val dialog = builder.create()
        dialog.show()
        val button = v.findViewById<Button>(R.id.btn_continue)
        button.setOnClickListener {
            view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_register_fragment_to_login_page_fragment) }
            dialog.dismiss()
        }
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun Toast(message: String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun failedAlertDialog(){
        val v = View.inflate(requireContext(), R.layout.failed_modal_dialog, null)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(v)

        val dialog = builder.create()
        dialog.show()
        val button = v.findViewById<Button>(R.id.btn_failed_continue)
        button.setOnClickListener {
            view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_register_fragment_to_login_page_fragment) }
        }
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}