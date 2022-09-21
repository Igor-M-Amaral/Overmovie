package com.example.igormattos.overmovie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.igormattos.overmovie.R
import com.example.igormattos.overmovie.utils.listener.ApiListener
import com.example.igormattos.overmovie.utils.Constants
import com.example.igormattos.overmovie.data.model.MoviesModel
import com.example.igormattos.overmovie.data.model.MoviesResult
import com.example.igormattos.overmovie.data.repository.MovieRepository

class MovieListViewModel(private val repository: MovieRepository) : ViewModel() {

    private var moviefilter = "popular"

    private val _movies = MutableLiveData<List<MoviesResult>>()
    val movies: LiveData<List<MoviesResult>> = _movies

    val nameTitle = MutableLiveData<String>()

    val progressBar = MutableLiveData<Boolean>()

    val errorMessage = MutableLiveData<String>()


    fun listMovie(filter: String) {
        progressBar.value = true
        moviefilter = filter
        val listener = object : ApiListener<MoviesModel> {
            override fun onSuccess(result: MoviesModel) {
                _movies.value = result.moviesResults
                progressBar.value = false
            }

            override fun onFailure(message: String) {
                errorMessage.postValue(message)
            }
        }

        when (filter) {
            Constants.FILTER.UPCOMING -> {
                repository.getMovieList(listener, filter)
                nameTitle.value = "Upcoming"
            }
            Constants.FILTER.POPULAR -> {
                repository.getMovieList(listener, filter)
                nameTitle.value = "Popular"
            }
            Constants.FILTER.TRENDING -> {
                repository.getTrendingMovies(listener)
                nameTitle.value = "Trending"
            }
            Constants.FILTER.RATED -> {
                repository.getMovieList(listener, filter)
                nameTitle.value = "Top rated"
            }
        }
    }

    fun searchPostsTitleContains(searchString: String) {
        val listener = object : ApiListener<MoviesModel> {
            override fun onSuccess(result: MoviesModel) {
                _movies.value = result.moviesResults
            }

            override fun onFailure(message: String) {
                errorMessage.postValue(message)
            }
        }
        repository.getSearch(searchString, listener)
    }
}