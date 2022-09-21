package com.examplepokedex.igormattos.tvshowapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.examplepokedex.igormattos.tvshowapp.utils.listener.ApiListener
import com.examplepokedex.igormattos.tvshowapp.utils.Constants
import com.examplepokedex.igormattos.tvshowapp.data.model.MoviesModel
import com.examplepokedex.igormattos.tvshowapp.data.model.MoviesResult
import com.examplepokedex.igormattos.tvshowapp.data.repository.MovieRepository

class MovieListViewModel(private val repository: MovieRepository) : ViewModel() {

    private var moviefilter = 0

    private val _movies = MutableLiveData<List<MoviesResult>>()
    val movies: LiveData<List<MoviesResult>> = _movies

    val nameTitle = MutableLiveData<String>()

    val progressBar = MutableLiveData<Boolean>()

    val errorMessage = MutableLiveData<String>()


    fun listMovie(filter: Int) {
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
                repository.getUpcomingList(listener)
                nameTitle.value = "Upcoming"

            }
            Constants.FILTER.POPULAR -> {
                repository.getPopularList(listener)
                nameTitle.value = "Popular"
            }
            Constants.FILTER.TRENDING -> {
                repository.getTrendingMovies(listener)
                nameTitle.value = "Trending"
            }
            Constants.FILTER.RATED -> {
                repository.getRated(listener)
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