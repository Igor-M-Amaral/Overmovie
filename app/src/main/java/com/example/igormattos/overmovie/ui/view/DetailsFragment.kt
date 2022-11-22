package com.example.igormattos.overmovie.ui.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.igormattos.overmovie.R
import com.example.igormattos.overmovie.data.model.MovieDB
import com.example.igormattos.overmovie.databinding.FragmentDetailsBinding
import com.example.igormattos.overmovie.ui.adapter.castadapter.CastAdapter
import com.example.igormattos.overmovie.ui.adapter.movieadapter.SimilarAdapter
import com.example.igormattos.overmovie.ui.video.YoutubePlay
import com.example.igormattos.overmovie.ui.viewmodel.MovieDetailsViewModel
import com.example.igormattos.overmovie.utils.Constants
import com.example.igormattos.overmovie.utils.listener.MovieListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: MovieDetailsViewModel by viewModel()
    private val adapterSimilar = SimilarAdapter()
    private val adapterCast = CastAdapter()
    private val args: DetailsFragmentArgs by navArgs()
    private val progressBar: ProgressBar by lazy {
        binding.progressbar
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        binding = FragmentDetailsBinding.inflate(layoutInflater)

        binding.fabFavoriteButton.setOnClickListener(this)
        binding.fabPlay.setOnClickListener(this)
        binding.buttonBack.setOnClickListener(this)

        val listener = object : MovieListener {
            override fun onDeleteMovie(movie: MovieDB){}

            override fun onListClick(id: Int) {
                val action = DetailsFragmentDirections.actionNavDetailsSelf(id)

                findNavController().navigate(action)
            }
        }
        viewModel.progressBar.observe(viewLifecycleOwner, Observer {
            if (it) showProgressBar() else (hideProgressBar())
        })

        adapterSimilar.attachListener(listener)

        observer()

        return binding.root
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fabFavoriteButton -> {
                viewModel.favoriteMovie()
                viewModel.checkFavorite()
            }

            R.id.button_back -> {
                findNavController().navigateUp()
            }
            R.id.fabPlay -> {
                if (viewModel.videos.value != null && viewModel.videos.value!!.results.isNotEmpty()){
                    val video = YoutubePlay(viewModel.videos.value!!.results.last().key)
                    video.show(childFragmentManager, "Video")
                }else{
                    Toast.makeText(activity, "Trailer not available", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun observer() {
        val id = args.id

        viewModel.getMovieById(id)
        viewModel.getCastList(id)
        viewModel.getSimilarMovies(id)
        viewModel.getVideoById(id)

        viewModel.cast.observe(viewLifecycleOwner, Observer {
            binding.recyclerView.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerView.adapter = adapterCast
            adapterCast.setCastList(it.cast)
        })

        viewModel.similar.observe(viewLifecycleOwner, Observer {
            binding.recyclerViewSimiliar.layoutManager =
                GridLayoutManager(activity, 3, LinearLayoutManager.VERTICAL, false)
            binding.recyclerViewSimiliar.adapter = adapterSimilar
            adapterSimilar.submitList(it)
        })

        viewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            binding.apply {
                textTitle.text = it.title
                textReleaseDate.text = it.release_date
                textOverview.text = it.overview
                textAverage.text = String.format("%.1f", it.vote_average)

                Glide.with(requireContext())
                    .load(Constants.URL.IMAGE_BASE + it.backdrop_path)
                    .into(imgMovieLargePoster)
            }
            checkFavorite()
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun checkFavorite() {
        viewModel.favorite.observe(viewLifecycleOwner, Observer {
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