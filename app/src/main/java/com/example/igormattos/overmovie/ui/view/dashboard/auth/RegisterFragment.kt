package com.example.igormattos.overmovie.ui.view.dashboard.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.igormattos.overmovie.databinding.FragmentRegisterBinding
import com.example.igormattos.overmovie.ui.viewmodel.AuthViewModel
import com.example.igormattos.overmovie.utils.methods.UtilsMethods
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {

    private val viewModel: AuthViewModel by viewModel()
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        binding = FragmentRegisterBinding.inflate(layoutInflater)

        return binding.root
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
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }
}