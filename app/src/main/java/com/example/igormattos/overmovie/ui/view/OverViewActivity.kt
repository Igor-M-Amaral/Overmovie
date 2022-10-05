package com.example.igormattos.overmovie.ui.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.igormattos.overmovie.R
import com.example.igormattos.overmovie.databinding.ActivityOverViewBinding
import com.example.igormattos.overmovie.utils.Constants
import com.example.igormattos.overmovie.data.model.MovieDB
import com.example.igormattos.overmovie.utils.listener.MovieListener
import com.example.igormattos.overmovie.ui.adapter.castadapter.CastAdapter
import com.example.igormattos.overmovie.ui.adapter.movieadapter.MovieAdapter
import com.example.igormattos.overmovie.ui.adapter.movieadapter.SimilarAdapter
import com.example.igormattos.overmovie.ui.viewmodel.OverViewViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class OverViewActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityOverViewBinding
    private val viewModel: OverViewViewModel by viewModel()
    private val adapterSimilar = SimilarAdapter()
    private val adapterCast = CastAdapter()
    private val progressBar: ProgressBar by lazy {
        binding.progressbar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityOverViewBinding.inflate(layoutInflater)

        binding.fabPlayButton.setOnClickListener(this)
        binding.buttonBack.setOnClickListener(this)

        val listener = object : MovieListener {
            override fun onDeleteById(movie: MovieDB) {
            }

            override fun onListClick(id: Int) {
                val intent = Intent(applicationContext, OverViewActivity::class.java)
                val bundle = Bundle()
                bundle.putInt("ID", id)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
        viewModel.progressBar.observe(this, Observer {
            if (it) showProgressBar() else (hideProgressBar())

        })

        adapterSimilar.attachListener(listener)


        observer()

        setContentView(binding.root)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fabPlayButton -> {
                viewModel.favoriteMovie()
                viewModel.checkFavorite()
            }

            R.id.button_back -> {
                finish()
            }
        }

    }

    private fun observer() {
        val id = intent.extras!!.getInt("ID")

        viewModel.getMovieById(id)
        viewModel.getCastList(id)
        viewModel.getSimilarMovies(id)

        viewModel.cast.observe(this, Observer {
            binding.recyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerView.adapter = adapterCast
            adapterCast.setCastList(it.cast)
        })

        viewModel.similar.observe(this, Observer {
            binding.recyclerViewSimiliar.layoutManager =
                GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
            binding.recyclerViewSimiliar.adapter = adapterSimilar
            adapterSimilar.submitList(it)
        })

        viewModel.movieDetails.observe(this, Observer {
            binding.apply {
                textTitle.text = it.title
                textReleaseDate.text = it.release_date
                textOverview.text = it.overview
                textAverage.text = String.format("%.1f", it.vote_average)
                textPopularity.text = String.format("%.0f", it.popularity)

                Glide.with(applicationContext)
                    .load(Constants.URL.IMAGE_BASE + it.backdrop_path)
                    .into(imgMovieLargePoster)
            }
            checkFavorite()
        })

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun checkFavorite() {
        viewModel.favorite.observe(this, Observer {
            binding.apply {
                if (it) {
                    fabPlayButton.setColorFilter(Color.RED)
                } else {
                    fabPlayButton.setColorFilter(Color.WHITE)
                }
            }
        })
        viewModel.checkFavorite()

    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}
