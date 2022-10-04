package com.example.hiiii

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.Navigation


class Fragment1 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_1, container, false)

        val btn = view.findViewById<AppCompatButton>(R.id.editProfileBTN)
        btn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fragment1_to_fragment2)
        }



        return  view
    }


}