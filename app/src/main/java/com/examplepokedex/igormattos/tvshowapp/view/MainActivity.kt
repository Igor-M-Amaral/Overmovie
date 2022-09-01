package com.examplepokedex.igormattos.tvshowapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
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

        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        observe()

        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPopularList()
        viewModel.getUpcomingList()

    }

    private fun observe() {
//        viewModel.popularMovies.observe(this, Observer {
//            binding.recyclerView.layoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
//            adapter = MovieAdapter(it.PopularResults)
//            binding.recyclerView.adapter = adapter
//        })
        viewModel.upcomingMovies.observe(this, Observer {
            binding.recyclerView.layoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
            adapter = MovieAdapter(it.UpcomingResults)
            binding.recyclerView.adapter = adapter
        })

    }
}