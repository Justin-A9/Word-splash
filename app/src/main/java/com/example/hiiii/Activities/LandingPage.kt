package com.example.hiiii.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hiiii.R
import com.example.hiiii.databinding.ActivityLandingPageBinding
import com.example.hiiii.fragments.homeFragment
import com.example.hiiii.fragments.menuFragment
import com.example.hiiii.fragments.playSquahFragment

class LandingPage : AppCompatActivity() {

    private lateinit var binding: ActivityLandingPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLandingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeFragment(homeFragment())

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        //actionBar?.hide()
        supportActionBar?.hide()




       binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.homeFragment -> changeFragment(homeFragment())
                R.id.playSquahFragment -> changeFragment(playSquahFragment())
                R.id.menuFragment -> changeFragment(menuFragment())
                else->{

                }
            }
         true
        }
    }


    fun changeFragment(fragment: Fragment){

        supportFragmentManager.beginTransaction().apply {

            replace(R.id.frameLayout, fragment)
            commit()
        }
    }


}