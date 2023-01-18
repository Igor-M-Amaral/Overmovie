package com.example.igormattos.overmovie.ui.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.igormattos.overmovie.R
import com.example.igormattos.overmovie.databinding.FragmentLoginBinding
import com.example.igormattos.overmovie.ui.viewmodel.AuthViewModel
import com.example.igormattos.overmovie.utils.methods.UtilsMethods
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: AuthViewModel by viewModel()
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()


        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        binding.textForget.setOnClickListener {

            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()

            findNavController()
                .navigate(action)
        }

        binding.imageGoogle.setOnClickListener {
            binding.buttonLogin.isEnabled = false
            binding.progressCircular.visibility = View.VISIBLE
            singInGoogle()
        }

        binding.imageTwitter.setOnClickListener {
            Toast.makeText(context, "TODO", Toast.LENGTH_LONG).show()

        }


    }

    override fun onResume() {
        super.onResume()
        binding.buttonLogin.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()

            val result = UtilsMethods.validationLogin(email, password)

            if (result.successful) {
                binding.buttonLogin.isEnabled = false
                binding.progressCircular.visibility = View.VISIBLE

                viewModel.login(email, password)

            } else {
                binding.buttonLogin.isEnabled = true
                binding.progressCircular.visibility = View.GONE
                Snackbar.make(binding.buttonLogin, "${result.error}", Snackbar.LENGTH_LONG).show()
            }
        }

        viewModel.verify.observe(this, Observer {
            if (it) {
                binding.buttonLogin.isEnabled = true
                binding.progressCircular.visibility = View.GONE

                findNavController().popBackStack(R.id.navigation_auth, true)
                findNavController().navigate(R.id.navigation_bottom)

            }
        })

        viewModel.message.observe(this, Observer {
            binding.buttonLogin.isEnabled = true
            binding.progressCircular.visibility = View.GONE
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()

        })
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {

                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                handleResults(task)
            }
        }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount? = task.result
            if (account != null) {
                viewModel.loginWithGoogle(account)
            }
        } else {
            Toast.makeText(context, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun singInGoogle() {
        val intent = googleSignInClient.signInIntent
        launcher.launch(intent)
    }
}