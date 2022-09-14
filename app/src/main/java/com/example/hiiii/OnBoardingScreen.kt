package com.example.hiiii

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.hiiii.authenticationScreen.authenticationScreens
import com.example.hiiii.databinding.FragmentOnBoardingScreenBinding


class OnBoardingScreen : Fragment(), ViewPager.OnPageChangeListener {

    private var _binding : FragmentOnBoardingScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var layoutRes: List<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentOnBoardingScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        setUpViewPager()

        binding.next.setOnClickListener {
            binding.viewPager.setCurrentItem(getItem(+1), true)
        }

        binding.getStarted.setOnClickListener {

            val intent = Intent(requireContext(), authScreens::class.java)
            startActivity(intent)
        }

        binding.skip.setOnClickListener {
            val intent = Intent(requireContext(), landingPage::class.java)
            startActivity(intent)
        }
        return view
    }

    private fun getItem(position: Int): Int {
        return binding.viewPager.currentItem + position
    }

    private fun setUpViewPager() {
        layoutRes = listOf(R.layout.first_screen, R.layout.second_screem, R.layout.third_screen)
        val adapter = ViewPagerAdapter(layoutRes)
        binding.viewPager.adapter = adapter
        binding.viewPager.addOnPageChangeListener(this)
        binding.circleIndicator.setViewPager(binding.viewPager)
    }


    inner class ViewPagerAdapter(private val layouts: List<Int>) : PagerAdapter() {
        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object` as View
        }

        override fun getCount(): Int {
            return layouts.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val layoutInflater = LayoutInflater.from(container.context)
            val layoutView = layoutInflater.inflate(layouts[position], container, false)
            container.addView(layoutView)
            return layoutView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }
    }


    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        toggleBtnVisibility(position)

    }

    override fun onPageScrollStateChanged(position: Int) {
    }


    private fun toggleBtnVisibility(position: Int) {

        when (position) {
            0 -> {
                binding.next.isEnabled = true
                binding.next.visibility = View.VISIBLE
                binding.getStarted.visibility = View.INVISIBLE
                binding.next.text= "Next"
            }
            1 -> {
                binding.next.isEnabled = true
                binding.next.visibility = View.VISIBLE
                binding.getStarted.visibility = View.INVISIBLE
                binding.next.text= "Next"
            }
            2 -> {
                binding.next.isEnabled = false
                binding.next.visibility = View.GONE
                binding.getStarted.visibility = View.VISIBLE
                binding.getStarted.isEnabled = true

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}