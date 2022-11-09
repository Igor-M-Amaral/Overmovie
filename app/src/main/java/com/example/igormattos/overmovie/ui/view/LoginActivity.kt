package com.example.igormattos.overmovie.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.igormattos.overmovie.R
import com.example.igormattos.overmovie.databinding.ActivityLoginBinding
import com.example.igormattos.overmovie.databinding.HomeFragmentBinding
import com.example.igormattos.overmovie.ui.viewmodel.AuthViewModel
import com.example.igormattos.overmovie.ui.viewmodel.MovieListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)
        supportActionBar?.hide()

        binding.textRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

    }

    override fun onStart() {
        super.onStart()
        binding.buttonLogin.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()

            viewModel.login(email, password)
        }
        viewModel.verify.observe(this, Observer {
            if (it){
                startActivity(Intent(this, MainActivity::class.java))
            }
        })
    }
}