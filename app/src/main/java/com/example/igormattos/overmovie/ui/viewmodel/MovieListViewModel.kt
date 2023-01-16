package com.example.igormattos.overmovie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.igormattos.overmovie.data.model.MoviesResult
import com.example.igormattos.overmovie.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import java.lang.NullPointerException

class MovieListViewModel(private val repository: MovieRepositoryImpl) : ViewModel() {

    private var moviefilter = "popular"

    private val _movies = MutableLiveData<Flow<PagingData<MoviesResult>>>(emptyFlow())
    val movies: LiveData<Flow<PagingData<MoviesResult>>> = _movies

    private val _search = MutableLiveData<Flow<PagingData<MoviesResult>>>(emptyFlow())
    val search: LiveData<Flow<PagingData<MoviesResult>>> = _search

    val errorMessage = MutableLiveData<String>()

    fun listMovie(filter: String) {
        moviefilter = filter
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (filter == "trending") {
                    val result = repository.getTrendingMovies()
                    _movies.postValue(result.cachedIn(viewModelScope))

                } else {
                    val result = repository.getMovieList(filter)
                    _movies.postValue(result.cachedIn(viewModelScope))
                }
            } catch (e: Exception) {
                errorMessage.postValue(e.message)
            }
        }
    }

    fun searchPostsTitleContains(searchString: String) {
        viewModelScope.launch {
            try {
                val result = repository.getSearch(searchString)
                _search.postValue(result.cachedIn(viewModelScope))
            } catch (e: NullPointerException) {
            }
        }
    }

}