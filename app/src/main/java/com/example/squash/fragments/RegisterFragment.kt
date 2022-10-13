package com.example.squash.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.squash.R
import com.example.squash.databinding.FragmentRegisterFragmentBinding
import com.example.squash.datasource.Constants
import com.example.squash.datasource.FireStoreData
import com.example.squash.datasource.Users
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterFragmentBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentRegisterFragmentBinding.inflate(inflater, container, false)

        navController = NavHostFragment.findNavController(this)
        val view = binding.root

        auth = Firebase.auth



        binding.iAlreadyHaveAnAccount.setOnClickListener {
            navigateTo(R.id.action_register_fragment_to_login_page_fragment)
        }
        binding.createAccount.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(Constants.USERNAME, binding.registerUserName.text.toString())
            registerUser()
        }

        binding.loginTextview.setOnClickListener {
            navigateTo(R.id.action_register_fragment_to_login_page_fragment)
        }

        return view
    }

    private fun navigateTo(id: Int) {
        navController.navigate(id)
    }


    private fun registerUser() {

        var email = binding.registerEmail.text.toString()
        var password = binding.registerPassword.text.toString()
        var confirmPassword = binding.registerConfirmPassword.text.toString()

        if (email.isEmpty()) {
            showErrorSnackBar("Email cannot be left empty", true)
            binding.progressBar.visibility = View.GONE
        } else if (!isValidEmail(email)) {
            showErrorSnackBar("Input a correct email", true)
            binding.progressBar.visibility = View.GONE
        } else if (!isValidPassword(password)) {
            showErrorSnackBar(
                "Password must contain a letter, a number and a symbol",
                true
            )
        } else if (password.isEmpty()) {
            showErrorSnackBar("Password cannot be left empty", true)
        } else if (binding.registerUserName.text.toString().isEmpty()) {
            showErrorSnackBar("UserName cannot be left blank", true)
        } else if (password.length < 6) {
            showErrorSnackBar("Password must be more than 6 characters", true)
            binding.progressBar.visibility = View.GONE
        } else if (password != confirmPassword) {
            showErrorSnackBar("Please fill in all the required information", true)
            binding.progressBar.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.VISIBLE
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        binding.progressBar.visibility = View.GONE
                        val myUser = Users(
                            binding.registerEmail.text.toString().trim { it <= ' ' },
                            binding.registerPassword.text.toString().trim { it <= ' ' },
                            binding.registerConfirmPassword.text.toString().trim { it <= ' ' },
                            binding.registerUserName.text.toString().trim { it <= ' ' },
                        )

                        binding.progressBar.visibility = View.GONE
                        successAlertDialog()
                        FireStoreData().registerUser(this, myUser)
                    } else {

                        // If sign in fails, display a message to the user.
                        showErrorSnackBar("Authentication Failed", true)
                        failedAlertDialog()
                        binding.progressBar.visibility = View.GONE
                    }
                }
        }
    }

    private fun isValidPassword(password: String?): Boolean {
        password?.let {
            val passwordPattern =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
            val passwordMatcher = Regex(passwordPattern)

            return passwordMatcher.find(password) != null
        } ?: return false
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun showErrorSnackBar(message: String, errorMessage: Boolean) {
        val snackBar = Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        )
        val snackbarView = snackBar.view

        if (errorMessage) {
            snackbarView.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.snackBarFailure
                )
            )
        } else {
            snackbarView.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.main_primary_color
                )
            )
        }
        snackBar.show()
    }

    private fun successAlertDialog() {
        val v = View.inflate(requireContext(), R.layout.success_modal_dialog, null)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(v)

        val dialog = builder.create()
        dialog.show()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun Toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun failedAlertDialog() {
        val v = View.inflate(requireContext(), R.layout.failed_modal_dialog, null)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(v)

        val dialog = builder.create()
        dialog.show()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}