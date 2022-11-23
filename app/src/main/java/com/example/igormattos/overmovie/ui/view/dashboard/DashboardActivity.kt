package com.example.igormattos.overmovie.ui.view.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.igormattos.overmovie.R
import com.example.igormattos.overmovie.databinding.ActivityDashboardBinding
import com.google.android.material.tabs.TabLayoutMediator

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)

        supportActionBar?.hide()

        setContentView(binding.root)

        val tabLayout = binding.tabLayout
        val viewpager = binding.viewPager

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        viewpager.adapter = adapter

        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.login)
                1 -> tab.text = getString(R.string.signup)
            }
        }.attach()
    }
}