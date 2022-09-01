package com.examplepokedex.igormattos.tvshowapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.examplepokedex.igormattos.tvshowapp.services.ApiListener
import com.examplepokedex.igormattos.tvshowapp.services.model.PopularResponse
import com.examplepokedex.igormattos.tvshowapp.services.model.UpcomingResponse
import com.examplepokedex.igormattos.tvshowapp.services.repository.MovieRepository

class MainViewModel: ViewModel() {

    private val _popularMovies = MutableLiveData<PopularResponse>()
    val popularMovies: LiveData<PopularResponse> = _popularMovies

    private val _upcomingMovies = MutableLiveData<UpcomingResponse>()
    val upcomingMovies: LiveData<UpcomingResponse> = _upcomingMovies

    private val repository = MovieRepository()

    fun getPopularList(){
        repository.getPopularList(object : ApiListener<PopularResponse>{
            override fun onSuccess(result: PopularResponse) {
                _popularMovies.value = result
            }

            override fun onFailure(message: String) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getUpcomingList(){
        repository.getUpcomingList(object : ApiListener<UpcomingResponse>{
            override fun onSuccess(result: UpcomingResponse) {
                _upcomingMovies.value = result
            }

            override fun onFailure(message: String) {
                TODO("Not yet implemented")
            }
        })
    }

}