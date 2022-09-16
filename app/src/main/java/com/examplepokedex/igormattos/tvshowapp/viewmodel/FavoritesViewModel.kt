package com.examplepokedex.igormattos.tvshowapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.examplepokedex.igormattos.tvshowapp.services.model.MovieDB
import com.examplepokedex.igormattos.tvshowapp.services.repository.local.FavoriteDatabase

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val _movies = MutableLiveData<List<MovieDB>>()
    val movies: LiveData<List<MovieDB>> = _movies

    private val databaseDao = FavoriteDatabase.getDataBase(application.applicationContext).getFavoriteDao()

    fun listFavorites(){
        _movies.value = databaseDao.getAllFavorites()
    }

}