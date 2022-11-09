package com.example.igormattos.overmovie.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.igormattos.overmovie.databinding.ActivityLoginBinding
import com.example.igormattos.overmovie.ui.viewmodel.AuthViewModel
import com.example.igormattos.overmovie.utils.methods.UtilsMethods
import com.google.android.material.snackbar.Snackbar
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

            val result = UtilsMethods.validationLogin(email, password)

            if (result.successful) {
                binding.buttonLogin.isEnabled = false
                binding.progressCircular.visibility = View.VISIBLE

                viewModel.login(email, password)

                viewModel.verify.observe(this, Observer {
                    if (it) {
                        binding.buttonLogin.isEnabled = true
                        binding.progressCircular.visibility = View.GONE
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                })
            } else {
                binding.buttonLogin.isEnabled = true
                binding.progressCircular.visibility = View.GONE
                Snackbar.make(binding.buttonLogin, "${result.error}", Snackbar.LENGTH_LONG).show()
            }

            viewModel.message.observe(this, Observer {
                binding.buttonLogin.isEnabled = true
                binding.progressCircular.visibility = View.GONE
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()

            })
        }

        binding.textForget.setOnClickListener {
            //TODO
        }
    }
}