package com.example.hiiii.datasource

import android.util.Log
import com.example.hiiii.fragments.Register_fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FireStoreData {

    private val mFirestore = FirebaseFirestore.getInstance()

    fun registerUser(fragment: Register_fragment, userinfo: Users) {

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


//        if (userinfo != null) {
//            mFirestore.collection(Constants.users)
//
//                .document(userinfo.id)
//                .set(userinfo, SetOptions.merge())
//                .addOnSuccessListener {
//
//
//                }
//                .addOnFailureListener {
//
//                }
//        }
//    }


}