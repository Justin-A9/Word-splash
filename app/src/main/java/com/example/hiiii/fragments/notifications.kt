package com.example.hiiii.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hiiii.Adapters.NotificationAdapter
import com.example.hiiii.R
import com.example.hiiii.data.NotificationDataClass
import com.example.hiiii.databinding.FragmentNotificationsBinding


class notifications : Fragment() {

    private lateinit var binding:FragmentNotificationsBinding
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)
        binding.notififcationsRecycler.layoutManager = LinearLayoutManager(requireContext())

        binding.baseline.setOnClickListener {
            navController.navigate(R.id.action_notifications_to_menuFragment)
        }

        val Messages = ArrayList<NotificationDataClass>()
        Messages.add(NotificationDataClass("Lorem Ipsum is simply dummy text of the printing and typesetting industry."))
        Messages.add(NotificationDataClass("Lorem Ipsum is simply dummy text of the printing and typesetting industry."))
        Messages.add(NotificationDataClass("Lorem Ipsum is simply dummy text of the printing and typesetting industry."))
        Messages.add(NotificationDataClass("Lorem Ipsum is simply dummy text of the printing and typesetting industry."))
        Messages.add(NotificationDataClass("Lorem Ipsum is simply dummy text of the printing and typesetting industry."))
        Messages.add(NotificationDataClass("Lorem Ipsum is simply dummy text of the printing and typesetting industry."))
        Messages.add(NotificationDataClass("Lorem Ipsum is simply dummy text of the printing and typesetting industry."))
        Messages.add(NotificationDataClass("Lorem Ipsum is simply dummy text of the printing and typesetting industry."))
        Messages.add(NotificationDataClass("Lorem Ipsum is simply dummy text of the printing and typesetting industry."))
        Messages.add(NotificationDataClass("Lorem Ipsum is simply dummy text of the printing and typesetting industry."))
        Messages.add(NotificationDataClass("Lorem Ipsum is simply dummy text of the printing and typesetting industry."))
        val adapter = NotificationAdapter(Messages)
        binding.notififcationsRecycler.adapter = adapter
        return binding.root
    }
}