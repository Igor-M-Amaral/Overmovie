package com.example.igormattos.overmovie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.igormattos.overmovie.utils.listener.ApiListener
import com.example.igormattos.overmovie.data.model.CastModel
import com.example.igormattos.overmovie.data.model.MovieDB
import com.example.igormattos.overmovie.data.model.MoviesModel
import com.example.igormattos.overmovie.data.model.MoviesResult
import com.example.igormattos.overmovie.data.repository.MovieRepository
import com.example.igormattos.overmovie.data.local.FavoriteDao

class OverViewViewModel(private val favoriteDao: FavoriteDao, private val repository: MovieRepository) : ViewModel(){


    private val _cast = MutableLiveData<CastModel>()
    val cast: LiveData<CastModel> = _cast

    private var _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _similar = MutableLiveData<MoviesModel>()
    val similar: LiveData<MoviesModel> = _similar

    private val _movieDetails = MutableLiveData<MoviesResult>()
    val movieDetails: LiveData<MoviesResult> = _movieDetails

    val errorMessage = MutableLiveData<String>()

    var favorite = MutableLiveData(false)

    fun getMovieById(id: Int){
        repository.getMovieById(id, object : ApiListener<MoviesResult> {
            override fun onSuccess(result: MoviesResult) {
                _movieDetails.value = result
            }

            override fun onFailure(message: String) {
                errorMessage.value = message
            }

        })
    }

    fun getCastList(id: Int) {
        repository.getCastList(id, object : ApiListener<CastModel> {
            override fun onSuccess(result: CastModel) {
                _cast.value = result
            }

            override fun onFailure(message: String) {
                errorMessage.value = message
            }
        })
    }

    fun getSimilarMovies(id: Int) {
        repository.getSimilarMovies(id, object : ApiListener<MoviesModel> {
            override fun onSuccess(result: MoviesModel) {
                _similar.value = result
            }

            override fun onFailure(message: String) {
                errorMessage.value = message
            }

        })
    }


    fun favoriteMovie(){
        movieDetails.value!!.apply {
            val movieDB = MovieDB(id, poster_path, vote_average, title, backdrop_path)

            if (favorite.value == true){
                favoriteDao.removeMovie(movieDB)
            }else{
                favoriteDao.save(movieDB)
            }
        }
    }

    fun checkFavorite(){
        val response = favoriteDao.favoriteExist(movieDetails.value!!.id)
        favorite.postValue(response)
    }
}