package com.examplepokedex.igormattos.tvshowapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.examplepokedex.igormattos.tvshowapp.services.ApiListener
import com.examplepokedex.igormattos.tvshowapp.services.model.MovieResponse
import com.examplepokedex.igormattos.tvshowapp.services.repository.MovieRepository

class MainViewModel: ViewModel() {

    private val _movie = MutableLiveData<MovieResponse>()
    val movie: LiveData<MovieResponse> = _movie

    private val repository = MovieRepository()

    fun getListMovie(){
        repository.getMovieList(object : ApiListener<MovieResponse>{
            override fun onSuccess(result: MovieResponse) {
                _movie.value = result
            }

            override fun onFailure(message: String) {
                TODO("Not yet implemented")
            }

        })
    }

}