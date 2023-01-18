package com.example.igormattos.overmovie.ui.view

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.igormattos.overmovie.R
import com.example.igormattos.overmovie.databinding.ActivityMainBinding
import com.example.igormattos.overmovie.ui.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: AuthViewModel by viewModel()

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
        setContentView(binding.root)

        setupToolbar()
        setupBottomNavigationView()

        viewModel.checkIfUserIsLogger()

        viewModel.verify.observe(this, Observer {
            if (!it) {
                findNavController(binding.fragmentContainerView.id)
                    .popBackStack(R.id.navigation_bottom, true)

                findNavController(binding.fragmentContainerView.id)
                    .navigate(R.id.navigation_auth)

            }
        })

        binding.fabExpand.setOnClickListener {
            buttonClicked()

        }
        binding.fabFavorites.setOnClickListener {
            buttonClicked()
            findNavController(binding.fragmentContainerView.id)
                .navigate(R.id.nav_favorites)

        }
        binding.fabLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.log_out_title))
                .setMessage(getString(R.string.log_out_message))
                .setPositiveButton(getString(R.string.log_out)) { _, _ ->
                    viewModel.logout()
                    buttonClicked()

                    findNavController(binding.fragmentContainerView.id)
                        .popBackStack(R.id.navigation_bottom, true)

                    findNavController(binding.fragmentContainerView.id)
                        .navigate(R.id.navigation_auth)

                }
                .setNeutralButton(getString(R.string.cancel), null)
                .show()
        }
    }

    private fun buttonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            binding.fabFavorites.startAnimation(fromBottom)
            binding.fabLogout.startAnimation(fromBottom)
        } else {
            binding.fabFavorites.startAnimation(toBottom)
            binding.fabLogout.startAnimation(toBottom)
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

    private fun setupBottomNavigationView() {
        val navHostFragment = getNavHostFragment()
        binding.bottomNavigationView.setupWithNavController(navHostFragment.navController)

    }

    private fun setupToolbar() {
        val navHostFragment = getNavHostFragment()
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        setVisibilityByDestination(navController)
    }

    private fun setVisibilityByDestination(navController: NavController) {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.nav_favorites -> {
                    binding.toolbar.visibility = View.VISIBLE
                    binding.fabExpand.visibility = View.GONE
                    binding.bottomNavigationView.visibility = View.GONE
                }

                R.id.nav_details, R.id.nav_login, R.id.nav_register -> {
                    binding.toolbar.visibility = View.GONE
                    binding.fabExpand.visibility = View.GONE
                    binding.bottomNavigationView.visibility = View.GONE
                    if (clicked){
                        buttonClicked()
                    }
                }
                else -> {
                    binding.toolbar.visibility = View.VISIBLE
                    binding.fabExpand.visibility = View.VISIBLE
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun getNavHostFragment(): NavHostFragment = supportFragmentManager
        .findFragmentById(binding.fragmentContainerView.id)
            as NavHostFragment

}


