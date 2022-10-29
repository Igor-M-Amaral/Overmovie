package com.example.igormattos.overmovie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.igormattos.overmovie.data.model.MovieDB
import com.example.igormattos.overmovie.data.local.FavoriteDao
import com.example.igormattos.overmovie.data.model.MoviesResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import java.lang.NullPointerException

class FavoritesViewModel(private val favoriteDao: FavoriteDao) : ViewModel() {

    private val _movies = MutableLiveData<List<MovieDB>>()
    val movies: LiveData<List<MovieDB>> = _movies

    private val _search = MutableLiveData<List<MovieDB>>()
    val search: LiveData<List<MovieDB>> = _search

     fun listFavorites(){
         viewModelScope.launch(Dispatchers.IO){
             _movies.postValue(favoriteDao.getAllFavorites())
         }
    }

    fun deleteFavorite(id: MovieDB) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteDao.removeMovie(id)
        }
    }

    fun searchPostsTitleContains(searchString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _search.postValue(favoriteDao.searchDataBase(searchString))
            } catch (e: NullPointerException) {
            }
        }
    }

}