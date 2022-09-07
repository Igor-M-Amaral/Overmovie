package com.examplepokedex.igormattos.tvshowapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.examplepokedex.igormattos.tvshowapp.services.ApiListener
import com.examplepokedex.igormattos.tvshowapp.services.constants.Constants
import com.examplepokedex.igormattos.tvshowapp.services.model.MoviesModel
import com.examplepokedex.igormattos.tvshowapp.services.repository.MovieRepository

class MainViewModel : ViewModel() {

    private var moviefilter = 0

    private val _movies = MutableLiveData<MoviesModel>()
    val movies: LiveData<MoviesModel> = _movies

    val nameTitle = MutableLiveData<String>()

    val errorMessage = MutableLiveData<String>()

    private val repository = MovieRepository()

    fun listMovie(filter: Int){
        moviefilter = filter
        val listener = object : ApiListener<MoviesModel>{
            override fun onSuccess(result: MoviesModel) {
                _movies.value = result
            }

            override fun onFailure(message: String) {
                errorMessage.postValue(message)
            }
        }

        when(filter) {
            Constants.FILTER.UPCOMING -> {
                repository.getUpcomingList(listener)
                nameTitle.value = "Upcoming"
            }
            Constants.FILTER.POPULAR -> {
            repository.getPopularList(listener)
                nameTitle.value = "Popular"
            }
            Constants.FILTER.TRENDING ->{
                repository.getTrendingMovies(listener)
                nameTitle.value = "Trending"
            }
        }
    }
}