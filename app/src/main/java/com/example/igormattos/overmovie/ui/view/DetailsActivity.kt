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
import com.example.igormattos.overmovie.utils.Constants
import com.example.igormattos.overmovie.data.model.MovieDB
import com.example.igormattos.overmovie.databinding.ActivityDetailsBinding
import com.example.igormattos.overmovie.utils.listener.MovieListener
import com.example.igormattos.overmovie.ui.adapter.castadapter.CastAdapter
import com.example.igormattos.overmovie.ui.adapter.movieadapter.SimilarAdapter
import com.example.igormattos.overmovie.ui.video.YoutubePlay
import com.example.igormattos.overmovie.ui.viewmodel.OverViewViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailsBinding
    private val viewModel: OverViewViewModel by viewModel()
    private val adapterSimilar = SimilarAdapter()
    private val adapterCast = CastAdapter()
    private val progressBar: ProgressBar by lazy {
        binding.progressbar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityDetailsBinding.inflate(layoutInflater)

        binding.fabFavoriteButton.setOnClickListener(this)
        binding.fabPlay.setOnClickListener(this)
        binding.buttonBack.setOnClickListener(this)

        val listener = object : MovieListener {
            override fun onDeleteMovie(movie: MovieDB) {
            }

            override fun onListClick(id: Int) {
                val intent = Intent(applicationContext, DetailsActivity::class.java)
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
            R.id.fabFavoriteButton -> {
                viewModel.favoriteMovie()
                viewModel.checkFavorite()
            }

            R.id.button_back -> {
                finish()
            }
            R.id.fabPlay -> {
                if (viewModel.videos.value != null && viewModel.videos.value!!.results.isNotEmpty()){
                    val video = YoutubePlay(viewModel.videos.value!!.results.last().key)
                    video.show(supportFragmentManager, "Video")
                }else{
                    Toast.makeText(applicationContext, "Trailer not available", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun observer() {
        val id = intent.extras!!.getInt("ID")

        viewModel.getMovieById(id)
        viewModel.getCastList(id)
        viewModel.getSimilarMovies(id)
        viewModel.getVideoById(id)

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
                    fabFavoriteButton.setColorFilter(Color.RED)
                } else {
                    fabFavoriteButton.setColorFilter(Color.WHITE)
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
