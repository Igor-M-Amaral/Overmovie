package com.example.igormattos.overmovie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.igormattos.overmovie.utils.Constants
import com.example.igormattos.overmovie.data.model.MoviesResult
import com.example.igormattos.overmovie.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.NullPointerException

class MovieListViewModel(private val repository: MovieRepository) : ViewModel() {

    private var moviefilter = "popular"

    private val _movies = MutableLiveData<List<MoviesResult>>()
    val movies: LiveData<List<MoviesResult>> = _movies

    val nameTitle = MutableLiveData<String>()

    val progressBar = MutableLiveData<Boolean>()

    val errorMessage = MutableLiveData<String>()


    fun listMovie(filter: String) {
        progressBar.value = true
        moviefilter = filter

        viewModelScope.launch {
            try {
                if (filter == Constants.FILTER.TRENDING) {
                    val result = repository.getTrendingMovies()
                    if (result != null) {
                        _movies.postValue(result.moviesResults)
                        nameTitle.value = filter.uppercase()
                        progressBar.value = false
                    }
                } else {
                    val result = repository.getMovieList(filter)
                    if (result != null) {
                        _movies.postValue(result.moviesResults)
                        progressBar.value = false
                        nameTitle.value = filter.uppercase()
                    }
                }
            } catch (e: Exception) {
                errorMessage.value = "Something went wrong!"
            }
        }
    }

    fun searchPostsTitleContains(searchString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getSearch(searchString)
                _movies.postValue(result!!.moviesResults)
            }catch (e: NullPointerException) { }
        }
    }
}