package com.examplepokedex.igormattos.tvshowapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.examplepokedex.igormattos.tvshowapp.databinding.ActivityMainBinding
import com.examplepokedex.igormattos.tvshowapp.services.viewmodel.MainVieModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainVieModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainVieModel::class.java]


        observe()

        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()
        viewModel.list()
    }

    private fun observe() {

    }
}