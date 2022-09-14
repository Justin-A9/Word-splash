package com.example.hiiii.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.hiiii.R
import com.example.hiiii.databinding.FragmentPlaySquahBinding


class playSquahFragment : Fragment() {

    private var _binding : FragmentPlaySquahBinding? = null
    private  val binding get() = _binding!!

    override fun onResume() {
        super.onResume()

        val category = resources.getStringArray(R.array.category)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down, category)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)


        val timer = resources.getStringArray(R.array.timer)
        val timerAdapter = ArrayAdapter(requireContext(), R.layout.drop_down, timer)
        binding.autoCompleteTextView2.setAdapter(timerAdapter)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentPlaySquahBinding.inflate(inflater, container, false)



        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}