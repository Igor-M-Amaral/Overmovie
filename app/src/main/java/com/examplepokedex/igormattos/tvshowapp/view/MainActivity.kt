package com.examplepokedex.igormattos.tvshowapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.examplepokedex.igormattos.tvshowapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val navHostFragment =
            (supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment)

        val navController = navHostFragment.navController
        binding.bottonNavigationView.setupWithNavController(navController)
    }
}