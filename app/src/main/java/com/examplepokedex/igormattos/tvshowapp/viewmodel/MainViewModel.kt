package com.examplepokedex.igormattos.tvshowapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.examplepokedex.igormattos.tvshowapp.services.ApiListener
import com.examplepokedex.igormattos.tvshowapp.services.model.MoviesModel
import com.examplepokedex.igormattos.tvshowapp.services.repository.MovieRepository

class MainViewModel: ViewModel() {

    private val _movies = MutableLiveData<MoviesModel>()
    val movies: LiveData<MoviesModel> = _movies

    private val _upcomingMovies = MutableLiveData<MoviesModel>()
    val upcomingMovies: LiveData<MoviesModel> = _upcomingMovies

    val errorMessage = MutableLiveData<String>()

    private val repository = MovieRepository()

    fun getPopularList(){
        repository.getPopularList(object : ApiListener<MoviesModel>{
            override fun onSuccess(result: MoviesModel) {
                _movies.value = result
            }

            override fun onFailure(message: String) {
                errorMessage.postValue(message)
            }
        })
    }

    fun getUpcomingList(){
        repository.getUpcomingList(object : ApiListener<MoviesModel>{
            override fun onSuccess(result: MoviesModel) {
                _upcomingMovies.value = result
            }

            override fun onFailure(message: String) {
                errorMessage.postValue(message)
            }
        })
    }

}