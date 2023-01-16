package com.example.igormattos.overmovie.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.igormattos.overmovie.R
import com.example.igormattos.overmovie.databinding.ActivityMainBinding
import com.example.igormattos.overmovie.ui.view.dashboard.DashboardActivity
import com.example.igormattos.overmovie.ui.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: AuthViewModel by viewModel()

    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.rotate_open_anim
        )
    }
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.rotate_close_anim
        )
    }
    private val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.from_bottom_anim
        )
    }
    private val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.to_bottom_anim
        )
    }

    private var clicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToolbar()
        setupBottomNavigationView()

        setContentView(binding.root)

        binding.fabExpand.setOnClickListener {
            buttonClicked()

        }
        binding.fabFavorites.setOnClickListener {
            findNavController(binding.fragmentContainerView.id)
                .navigate(R.id.nav_favorites)
        }
        binding.fabLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.log_out_title))
                .setMessage(getString(R.string.log_out_message))
                .setPositiveButton(getString(R.string.log_out)) { _, _ ->
                    viewModel.logout()
                    startActivity(Intent(this, DashboardActivity::class.java))
                    finish()
                }
                .setNeutralButton(getString(R.string.cancel), null)
                .show()
        }
    }

    private fun setupBottomNavigationView() {
        val navHostFragment = getNavHostFragment()
        binding.bottomNavigationView.setupWithNavController(navHostFragment.navController)

    }

    private fun setupToolbar() {
        val navHostFragment = getNavHostFragment()
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener{ controller, destinaiton, arguments ->
            when(destinaiton.id){
                R.id.nav_details ->{
                    binding.toolbar.visibility = View.GONE
                }
                else ->{
                    binding.toolbar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun getNavHostFragment(): NavHostFragment = supportFragmentManager
        .findFragmentById(binding.fragmentContainerView.id)
            as NavHostFragment

    private fun buttonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            binding.fabFavorites.startAnimation(fromBottom)
            binding.fabLogout.startAnimation(fromBottom)
            binding.fabExpand.startAnimation(rotateOpen)
        } else {
            binding.fabFavorites.startAnimation(toBottom)
            binding.fabLogout.startAnimation(toBottom)
            binding.fabExpand.startAnimation(rotateClose)
        }
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            binding.fabFavorites.visibility = View.VISIBLE
            binding.fabLogout.visibility = View.VISIBLE
        } else {
            binding.fabFavorites.visibility = View.GONE
            binding.fabLogout.visibility = View.GONE
        }
    }
}


