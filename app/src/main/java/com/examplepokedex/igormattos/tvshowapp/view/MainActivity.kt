package com.examplepokedex.igormattos.tvshowapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.examplepokedex.igormattos.tvshowapp.databinding.ActivityMainBinding
import com.examplepokedex.igormattos.tvshowapp.view.adapter.MovieAdapter
import com.examplepokedex.igormattos.tvshowapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

//        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//
//        binding.recyclerView.adapter = adapter

        observe()

        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getListMovie()

    }

    private fun observe() {
        viewModel.movie.observe(this, Observer {
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            adapter = MovieAdapter(it.results)
            binding.recyclerView.adapter = adapter

        })

    }
}