package com.examplepokedex.igormattos.tvshowapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.examplepokedex.igormattos.tvshowapp.services.ApiListener
import com.examplepokedex.igormattos.tvshowapp.services.model.TvShowItem
import com.examplepokedex.igormattos.tvshowapp.services.repository.TvShowRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TvShowRepository()

    private val _shows = MutableLiveData<List<TvShowItem>>()
    val shows: LiveData<List<TvShowItem>> = _shows


    fun list(){
        repository.listTvShows(object : ApiListener<List<TvShowItem>>{
            override fun onSuccess(result: List<TvShowItem>) {
                _shows.value = result
            }

            override fun onFailure(message: String) {

            }

        })



    }

}