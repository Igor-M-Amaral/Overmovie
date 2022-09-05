package com.examplepokedex.igormattos.tvshowapp.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.examplepokedex.igormattos.tvshowapp.databinding.HomeFragmentBinding
import com.examplepokedex.igormattos.tvshowapp.services.model.MoviesResult
import com.examplepokedex.igormattos.tvshowapp.view.adapter.movieadapter.MovieAdapter
import com.examplepokedex.igormattos.tvshowapp.viewmodel.MainViewModel

class PopularFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = HomeFragmentBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.getPopularList()

        observe()

        binding.textSelected.text = "Popular"

        return binding.root
    }

    private fun observe() {
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            binding.recyclerView.layoutManager =
                GridLayoutManager(activity, 3, LinearLayoutManager.VERTICAL, false)
            adapter = MovieAdapter {
                openOverView(it)
            }
            binding.recyclerView.adapter = adapter
            adapter.setMovieList(it.moviesResults)
        })
    }

    private fun openOverView(moviesResult: MoviesResult) {
        val intent = Intent(activity, OverViewActivity::class.java)
        intent.putExtra("ID", moviesResult.id)
        intent.putExtra("TITLE", moviesResult.title)
        intent.putExtra("BACKDROP_PATH", moviesResult.backdrop_path)
        intent.putExtra("DATE", moviesResult.release_date)
        intent.putExtra("POPULARITY", moviesResult.popularity)
        intent.putExtra("OVERVIEW", moviesResult.overview)
        intent.putExtra("VOTE", moviesResult.vote_average)
        startActivity(intent)
    }

}