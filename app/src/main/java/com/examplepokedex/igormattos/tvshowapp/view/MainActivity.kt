package com.examplepokedex.igormattos.tvshowapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.examplepokedex.igormattos.tvshowapp.databinding.ActivityMainBinding
import com.examplepokedex.igormattos.tvshowapp.services.model.MoviesResult
import com.examplepokedex.igormattos.tvshowapp.view.adapter.movieadapter.MovieAdapter
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

        viewModel.getPopularList()
        viewModel.getUpcomingList()

        observe()

        setContentView(binding.root)
    }


    private fun observe() {
        viewModel.upcomingMovies.observe(this, Observer {
            binding.recyclerView.layoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
            adapter = MovieAdapter{
                openOverView(it)
            }
            binding.recyclerView.adapter = adapter
            adapter.setMovieList(it.moviesResults)
        })
    }

    private fun openOverView(moviesResult: MoviesResult) {
        val intent = Intent(applicationContext, OverViewActivity::class.java)
        intent.putExtra("ID", moviesResult.id)
        intent.putExtra("TITLE", moviesResult.title)
        intent.putExtra("BACKDROP_PATH", moviesResult.backdrop_path)
        intent.putExtra("DATE", moviesResult.release_date)
        intent.putExtra("POPULARITY", moviesResult.popularity)
        intent.putExtra("OVERVIEW", moviesResult.overview)
        startActivity(intent)
    }
}