package com.example.igormattos.overmovie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.igormattos.overmovie.data.model.CastModel
import com.example.igormattos.overmovie.data.model.MovieDB
import com.example.igormattos.overmovie.data.model.MoviesModel
import com.example.igormattos.overmovie.data.model.MoviesResult
import com.example.igormattos.overmovie.data.repository.MovieRepository
import com.example.igormattos.overmovie.data.local.FavoriteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OverViewViewModel(
    private val favoriteDao: FavoriteDao,
    private val repository: MovieRepository
) : ViewModel() {


    private val _cast = MutableLiveData<CastModel>()
    val cast: MutableLiveData<CastModel> = _cast

    private var _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _similar = MutableLiveData<MoviesModel>()
    val similar: LiveData<MoviesModel> = _similar

    private val _movieDetails = MutableLiveData<MoviesResult>()
    val movieDetails: MutableLiveData<MoviesResult> = _movieDetails

    val errorMessage = MutableLiveData<String>()

    val progressBar = MutableLiveData<Boolean>()

    var favorite = MutableLiveData(false)

    fun getMovieById(id: Int) {
        viewModelScope.launch {
            val result = repository.getMovieById(id)
            _movieDetails.postValue(result!!)
        }
    }

    fun getCastList(id: Int) {
        progressBar.value = true
        viewModelScope.launch{
            val result = repository.getCastList(id)
            _cast.postValue(result!!)
            progressBar.value = false
        }
    }

    fun getSimilarMovies(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getSimilarMovies(id)
            _similar.postValue(result!!)
        }
    }


    fun favoriteMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            movieDetails.value!!.apply {
                val movieDB = MovieDB(id, poster_path, vote_average, title, backdrop_path)
                if (favorite.value == true) {
                    favoriteDao.removeMovie(movieDB)
                } else {
                    favoriteDao.save(movieDB)
                }
            }
        }
    }

    fun checkFavorite() {
        viewModelScope.launch(Dispatchers.IO){
            val response = favoriteDao.favoriteExist(movieDetails.value!!.id)
            favorite.postValue(response)
        }
    }
}