package com.example.squash.datasource

import android.content.ContentValues.TAG
import android.util.Log
import com.example.squash.fragments.RegisterFragment
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class FireStoreData {

    private val mFirestore = FirebaseFirestore.getInstance()

    fun registerUser(fragment: RegisterFragment, userinfo: Users) {

        val db = Firebase.firestore

        db.collection(Constants.users)
            .add(userinfo)
            .addOnSuccessListener { documentReference ->
                Log.d("Me", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("Me", "Error adding document", e)
            }

    }

    fun getUserName(): String? {
        val db = Firebase.firestore
        val auth = FirebaseAuth.getInstance()
        val docRef: DocumentReference = db.collection(Constants.users).document(auth.uid!!)
        docRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: " + task.result.data)
                } else {
                    Log.d(TAG, "No such document")
                }
            } else {
                Log.d(TAG, "get failed with ", task.exception)
            }
        }
        return Users().username
    }
    fun google(){
        val signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(true)
                    .build())
            .build()
    }


}