package com.examplepokedex.igormattos.tvshowapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.examplepokedex.igormattos.tvshowapp.services.repository.listener.ApiListener
import com.examplepokedex.igormattos.tvshowapp.services.model.CastModel
import com.examplepokedex.igormattos.tvshowapp.services.model.MovieDB
import com.examplepokedex.igormattos.tvshowapp.services.model.MoviesModel
import com.examplepokedex.igormattos.tvshowapp.services.model.MoviesResult
import com.examplepokedex.igormattos.tvshowapp.services.repository.MovieRepository
import com.examplepokedex.igormattos.tvshowapp.services.repository.local.FavoriteDao

class OverViewViewModel(private val favoriteDao: FavoriteDao) : ViewModel(){

    private val repository = MovieRepository()


    private val _cast = MutableLiveData<CastModel>()
    val cast: LiveData<CastModel> = _cast

    private var _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _similar = MutableLiveData<MoviesModel>()
    val similar: LiveData<MoviesModel> = _similar

    private val _movieDetails = MutableLiveData<MoviesResult>()
    val movieDetails: LiveData<MoviesResult> = _movieDetails

    var favorite = MutableLiveData(false)

    fun getMovieById(id: Int){
        repository.getMovieById(id, object : ApiListener<MoviesResult>{
            override fun onSuccess(result: MoviesResult) {
                _movieDetails.value = result
            }

            override fun onFailure(message: String) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getCastList(id: Int) {
        repository.getCastList(id, object : ApiListener<CastModel> {
            override fun onSuccess(result: CastModel) {
                _cast.value = result
            }

            override fun onFailure(message: String) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getSimilarMovies(id: Int) {
        repository.getSimilarMovies(id, object : ApiListener<MoviesModel> {
            override fun onSuccess(result: MoviesModel) {
                _similar.value = result
            }

            override fun onFailure(message: String) {
                TODO("Not yet implemented")
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