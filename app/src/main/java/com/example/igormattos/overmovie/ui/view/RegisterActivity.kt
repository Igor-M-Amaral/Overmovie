package com.example.igormattos.overmovie.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.igormattos.overmovie.databinding.ActivityRegisterBinding
import com.example.igormattos.overmovie.ui.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)
        supportActionBar?.hide()
    }

    override fun onStart() {
        super.onStart()

        binding.buttonContinue.setOnClickListener {
            val userName = binding.editUsername.text.toString()
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()

            viewModel.register(email, userName, password)
        }

        viewModel.verify.observe(this, Observer {
            if (it){
                startActivity(Intent(this, LoginActivity::class.java))
            }
        })

    }

}