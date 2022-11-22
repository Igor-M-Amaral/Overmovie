package com.example.igormattos.overmovie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.igormattos.overmovie.data.model.CastModel
import com.example.igormattos.overmovie.data.model.MovieDB
import com.example.igormattos.overmovie.data.model.MoviesResult
import com.example.igormattos.overmovie.data.local.FavoriteDao
import com.example.igormattos.overmovie.data.model.MovieVideo
import com.example.igormattos.overmovie.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val favoriteDao: FavoriteDao,
    private val repository: MovieRepositoryImpl
) : ViewModel() {

    private val _cast = MutableLiveData<CastModel>()
    val cast: MutableLiveData<CastModel> = _cast

    private var _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _similar = MutableLiveData<List<MoviesResult>>()
    val similar: LiveData<List<MoviesResult>> = _similar

    private val _movieDetails = MutableLiveData<MoviesResult>()
    val movieDetails: MutableLiveData<MoviesResult> = _movieDetails

    private val _videos = MutableLiveData<MovieVideo>()
    val videos: MutableLiveData<MovieVideo> = _videos

    val errorMessage = MutableLiveData<String>()

    val progressBar = MutableLiveData<Boolean>()

    var favorite = MutableLiveData(false)

    fun getMovieById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getMovieById(id)
            _movieDetails.postValue(result!!)
        }
    }

    fun getCastList(id: Int) {
        progressBar.postValue(true)
        viewModelScope.launch(Dispatchers.IO){
            val result = repository.getCastList(id)
            _cast.postValue(result!!)
            progressBar.postValue(false)
        }
    }

    fun getSimilarMovies(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getSimilarMovies(id)
            _similar.postValue(result?.moviesResults)
        }
    }

    fun getVideoById(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getVideoById(id)
            _videos.postValue(result!!)
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