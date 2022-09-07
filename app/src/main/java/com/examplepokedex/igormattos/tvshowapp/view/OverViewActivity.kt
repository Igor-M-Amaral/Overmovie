package com.examplepokedex.igormattos.tvshowapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.examplepokedex.igormattos.tvshowapp.databinding.ActivityOverViewBinding
import com.examplepokedex.igormattos.tvshowapp.services.model.MoviesResult
import com.examplepokedex.igormattos.tvshowapp.view.adapter.castadapter.CastAdapter
import com.examplepokedex.igormattos.tvshowapp.view.adapter.movieadapter.MovieAdapter
import com.examplepokedex.igormattos.tvshowapp.viewmodel.OverViewViewModel


class OverViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOverViewBinding
    private lateinit var viewModel: OverViewViewModel
    private lateinit var adapterSimilar: MovieAdapter
    private val adapterCast = CastAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityOverViewBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[OverViewViewModel::class.java]

        intent.extras?.let {
            viewModel.setBundle(it, binding.imgMovieLargePoster)
            viewModel.getCastList(it.getInt("ID"))
            viewModel.getSimilarMovies(it.getInt("ID"))
        }

        binding.buttonBack.setOnClickListener {
            finish()
        }

        observer()

        setContentView(binding.root)
    }

    private fun observer() {
        viewModel.title.observe(this, Observer { title ->
            binding.textTitle.text = title
        })
        viewModel.date.observe(this, Observer { date ->
            binding.textReleaseDate.text = date
        })
        viewModel.popularity.observe(this, Observer { popularity ->
            binding.textPopularity.text = popularity
        })
        viewModel.overview.observe(this, Observer { overview ->
            binding.textOverview.text = overview
        })
        viewModel.vote.observe(this, Observer { vote ->
            binding.textAverage.text = vote
        })
        viewModel.cast.observe(this, Observer {
            binding.recyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerView.adapter = adapterCast
            adapterCast.setCastList(it.cast)
        })
        viewModel.movies.observe(this, Observer {
            binding.recyclerViewSimiliar.layoutManager =
                GridLayoutManager(this,3, LinearLayoutManager.VERTICAL, false)
            adapterSimilar = MovieAdapter {
                openOverView(it)
            }
            binding.recyclerViewSimiliar.adapter = adapterSimilar
            adapterSimilar.setMovieList(it.moviesResults)
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
        intent.putExtra("VOTE", moviesResult.vote_average)
        startActivity(intent)

    }
}
