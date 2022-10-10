package com.example.hiiii.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.hiiii.R

class AuthScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_screens)

        supportActionBar?.hide()


    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


}