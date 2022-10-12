package com.example.squash.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.squash.activities.LandingPage
import com.example.squash.R
import com.example.squash.databinding.FragmentLoginPageFragmentBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginPageFragment : Fragment() {

    private var _binding : FragmentLoginPageFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private val RC_SIGN_IN = 1000



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentLoginPageFragmentBinding.inflate(inflater, container, false)

        val view = binding.root

        auth = Firebase.auth

        binding.googleLogo.setOnClickListener{

            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("191749595561-s55cihbdf39i4d8pqa49gfhr51unald4.apps.googleusercontent.com")
                .requestEmail()
                .build()


         val googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
            googleSignInClient.signInIntent.also {
                startActivityForResult(it, RC_SIGN_IN)
            }

        }

        binding.tvForgotPassword.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.action_login_page_fragment_to_forgot_password_fragment3)
        }

        binding.signInWithGoogle.setOnClickListener{

            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("191749595561-s55cihbdf39i4d8pqa49gfhr51unald4.apps.googleusercontent.com")
                .requestEmail()
                .build()


            val googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
            googleSignInClient.signInIntent.also {
                startActivityForResult(it, RC_SIGN_IN)
            }

        }

        binding.loginBtn.setOnClickListener {
            binding.progressBar2.visibility = View.VISIBLE
            loginUser()
        }

        binding.DontHaveAnAccount.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.action_login_page_fragment_to_register_fragment)
        }

        binding.signUp.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.action_login_page_fragment_to_register_fragment)
        }

        return  view

    }


    private fun googleAuthForFirebase(account: GoogleSignInAccount){
        val firebaseCredential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(firebaseCredential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    if (user != null) {

                        val intent = Intent(requireContext(), LandingPage::class.java)
                        activity?.startActivity(intent)
                    }
                } else {
                    android.widget.Toast.makeText(requireContext(), "Operation failed", android.widget.Toast.LENGTH_SHORT).show()
                }

            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data).result
            googleAuthForFirebase(task)
        }
    }


    private fun loginUser(){
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(requireContext(), LandingPage::class.java)
                    activity?.startActivity(intent)
                    Toast("You have successfully logged in")
                    binding.progressBar2.visibility = View.GONE
                } else {
                    // If sign in fails, display a message to the user.
                   Toast("Authentication Failed")
                    binding.progressBar2.visibility = View.GONE
                }
            }
    }


    fun Toast(message: String){
        android.widget.Toast.makeText(requireContext(), message, android.widget.Toast.LENGTH_SHORT).show()
    }

//    private fun checkLoggedInState(){
//        if (auth.currentUser != null){
//            startActivity(Intent(requireContext(), HomeScreen::class.java))
//        }else{
//            Toast("PLease Log in")
//        }
//    }


}