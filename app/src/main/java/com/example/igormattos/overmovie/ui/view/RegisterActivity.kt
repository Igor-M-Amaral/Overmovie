package com.example.igormattos.overmovie.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.igormattos.overmovie.databinding.ActivityRegisterBinding
import com.example.igormattos.overmovie.ui.viewmodel.AuthViewModel
import com.example.igormattos.overmovie.utils.methods.UtilsMethods
import com.google.android.material.snackbar.Snackbar
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
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()
            val confirmPassword = binding.editUsername.text.toString()

            val result = UtilsMethods.validationRegister(email, password, confirmPassword)

            if (result.successful) {
                binding.buttonContinue.isEnabled = false
                binding.progressCircular.visibility = View.VISIBLE

                viewModel.register(email, password)

                viewModel.verify.observe(this, Observer {
                    if (it) {
                        binding.buttonContinue.isEnabled = true
                        binding.progressCircular.visibility = View.GONE
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                })
            } else {
                binding.buttonContinue.isEnabled = true
                binding.progressCircular.visibility = View.GONE
                Snackbar.make(binding.buttonContinue, "${result.error}", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

        viewModel.message.observe(this, Observer {
            binding.buttonContinue.isEnabled = true
            binding.progressCircular.visibility = View.GONE
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

}