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
import com.example.igormattos.overmovie.databinding.FragmentDetailsBinding
import com.example.igormattos.overmovie.ui.adapter.castadapter.CastAdapter
import com.example.igormattos.overmovie.ui.adapter.movieadapter.SimilarAdapter
import com.example.igormattos.overmovie.ui.video.YoutubePlay
import com.example.igormattos.overmovie.ui.viewmodel.MovieDetailsViewModel
import com.example.igormattos.overmovie.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: MovieDetailsViewModel by viewModel()
    private val adapterSimilar = SimilarAdapter { movieId ->
        onItemClicked(movieId)
    }

    private val args: DetailsFragmentArgs by navArgs()
    private val progressBar: ProgressBar by lazy {
        binding.progressbar
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabFavoriteButton.setOnClickListener(this)
        binding.fabPlay.setOnClickListener(this)
        binding.buttonBack.setOnClickListener(this)

        viewModel.progressBar.observe(viewLifecycleOwner, Observer {
            if (it) showProgressBar() else (hideProgressBar())
        })

        observer()
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
                if (viewModel.videos.value != null && viewModel.videos.value!!.results.isNotEmpty()) {
                    val video = YoutubePlay(viewModel.videos.value!!.results.last().key)
                    video.show(childFragmentManager, "Video")
                } else {
                    Toast.makeText(
                        activity,
                        getString(R.string.trailer_not_availabe),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun onItemClicked(movieId: Int) {
        val action = DetailsFragmentDirections.actionNavDetailsSelf(movieId)

        findNavController().navigate(action)
    }


    private fun observer() {
        viewModel.getMovieById(args.id)
        viewModel.getCastList(args.id)
        viewModel.getSimilarMovies(args.id)
        viewModel.getVideoById(args.id)

        viewModel.cast.observe(viewLifecycleOwner, Observer {
            binding.recyclerView.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerView.adapter = CastAdapter(it.cast)
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