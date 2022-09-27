package com.example.igormattos.overmovie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.igormattos.overmovie.data.model.MovieDB
import com.example.igormattos.overmovie.data.local.FavoriteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(private val favoriteDao: FavoriteDao) : ViewModel() {

    private val _movies = MutableLiveData<List<MovieDB>>()
    val movies: LiveData<List<MovieDB>> = _movies

    val progressBar = MutableLiveData<Boolean>()


     fun listFavorites(){
         viewModelScope.launch{
             progressBar.value = true
             _movies.value = favoriteDao.getAllFavorites()
             progressBar.value = false
         }
    }

    fun deleteFavorite(id: MovieDB) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteDao.removeMovie(id)
        }
    }

}