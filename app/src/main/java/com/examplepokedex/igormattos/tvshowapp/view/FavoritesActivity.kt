package com.examplepokedex.igormattos.tvshowapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.examplepokedex.igormattos.tvshowapp.databinding.ActivityFavoritesBinding
import com.examplepokedex.igormattos.tvshowapp.databinding.HomeFragmentBinding
import com.examplepokedex.igormattos.tvshowapp.view.adapter.movieadapter.MovieAdapter
import com.examplepokedex.igormattos.tvshowapp.viewmodel.FavoritesViewModel
import com.examplepokedex.igormattos.tvshowapp.viewmodel.MovieListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding
    private lateinit var viewModel: FavoritesViewModel
//    private lateinit var adapter: MovieAdapter

    private val progressBar: ProgressBar by lazy {
        binding.mainProgressbar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        viewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]

        viewModel.listFavorites()

        binding = ActivityFavoritesBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }




}