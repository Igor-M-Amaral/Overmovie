package com.example.igormattos.overmovie.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.igormattos.overmovie.R
import com.example.igormattos.overmovie.databinding.ActivitySplashScreenBinding
import com.example.igormattos.overmovie.ui.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        binding.imgSplash.alpha = 0.2f
        binding.imgSplash.animate().setDuration(800).alpha(1f).withEndAction {

            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
        setContentView(binding.root)
    }
}
