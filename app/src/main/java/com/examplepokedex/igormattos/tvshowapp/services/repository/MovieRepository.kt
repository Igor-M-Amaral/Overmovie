package com.examplepokedex.igormattos.tvshowapp.services.repository

import com.examplepokedex.igormattos.tvshowapp.services.repository.listener.ApiListener
import com.examplepokedex.igormattos.tvshowapp.services.MovieService
import com.examplepokedex.igormattos.tvshowapp.services.model.CastModel
import com.examplepokedex.igormattos.tvshowapp.services.model.MoviesModel

class MovieRepository : BaseRepository() {

    private val remote = RetrofitClient.getService(MovieService::class.java)

    fun getPopularList(listener: ApiListener<MoviesModel>) {
        val call = remote.getPopularList()

        executeCall(call, listener)
    }

    fun getUpcomingList(listener: ApiListener<MoviesModel>) {
        val call = remote.getUpcomingList()

        executeCall(call, listener)
    }

    fun getRated(listener: ApiListener<MoviesModel>) {
        val call = remote.getRated()

        executeCall(call, listener)
    }

    fun getCastList(id: Int, listener: ApiListener<CastModel>) {
        val call = remote.getCastList(id)

        executeCall(call, listener)
    }


    fun getSimilarMovies(id: Int, listener: ApiListener<MoviesModel>) {
        val call = remote.getSimilarMovies(id)

        executeCall(call, listener)
    }

    fun getTrendingMovies(listener: ApiListener<MoviesModel>){
        val call = remote.getTrendingMovies()

        executeCall(call, listener)
    }

    fun getSearch(name: String, listener: ApiListener<MoviesModel>){
        val call = remote.getSearch(name)

        executeCall(call, listener)
    }
}
