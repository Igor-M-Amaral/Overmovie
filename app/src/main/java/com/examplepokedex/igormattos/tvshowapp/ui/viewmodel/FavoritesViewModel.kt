package com.examplepokedex.igormattos.tvshowapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.examplepokedex.igormattos.tvshowapp.data.model.MovieDB
import com.examplepokedex.igormattos.tvshowapp.data.local.FavoriteDao

class FavoritesViewModel(private val favoriteDao: FavoriteDao) : ViewModel() {

    private val _movies = MutableLiveData<List<MovieDB>>()
    val movies: LiveData<List<MovieDB>> = _movies

    val progressBar = MutableLiveData<Boolean>()


    fun listFavorites(){
        progressBar.value = true
        _movies.value = favoriteDao.getAllFavorites()
        progressBar.value = false
    }

    fun deleteFavorite(id: MovieDB) {
        favoriteDao.removeMovie(id)
    }

}