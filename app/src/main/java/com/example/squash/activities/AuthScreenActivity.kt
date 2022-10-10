package com.example.squash.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.squash.R

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