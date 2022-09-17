package com.examplepokedex.igormattos.tvshowapp.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.examplepokedex.igormattos.tvshowapp.R
import com.examplepokedex.igormattos.tvshowapp.databinding.ActivityOverViewBinding
import com.examplepokedex.igormattos.tvshowapp.services.constants.Constants
import com.examplepokedex.igormattos.tvshowapp.services.model.MovieDB
import com.examplepokedex.igormattos.tvshowapp.services.model.MoviesResult
import com.examplepokedex.igormattos.tvshowapp.services.repository.listener.MovieListener
import com.examplepokedex.igormattos.tvshowapp.view.adapter.castadapter.CastAdapter
import com.examplepokedex.igormattos.tvshowapp.view.adapter.movieadapter.MovieAdapter
import com.examplepokedex.igormattos.tvshowapp.viewmodel.OverViewViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class OverViewActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityOverViewBinding
    private val viewModel: OverViewViewModel by viewModel()
    private  val adapterSimilar = MovieAdapter()
    private val adapterCast = CastAdapter()

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

        adapterSimilar.attachListener(listener)

        observer()

        setContentView(binding.root)
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
                GridLayoutManager(this,3, LinearLayoutManager.VERTICAL, false)
            binding.recyclerViewSimiliar.adapter = adapterSimilar
            adapterSimilar.submitList(it.moviesResults)
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
    }


    private fun checkFavorite(){
        viewModel.favorite.observe(this, Observer {
            binding.apply {
                if (it){
                    fabPlayButton.setColorFilter(Color.RED)
                } else{
                    fabPlayButton.setColorFilter(Color.WHITE)

                }
            }
        })
        viewModel.checkFavorite()

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.fabPlayButton -> {
                viewModel.favoriteMovie()
                viewModel.checkFavorite()
            }

            R.id.button_back -> {
                finish()
            }
        }

    }

}
