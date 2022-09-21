package com.examplepokedex.igormattos.tvshowapp.data.repository

import com.examplepokedex.igormattos.tvshowapp.utils.listener.ApiListener
import com.examplepokedex.igormattos.tvshowapp.data.api.MovieService
import com.examplepokedex.igormattos.tvshowapp.data.model.CastModel
import com.examplepokedex.igormattos.tvshowapp.data.model.MoviesModel
import com.examplepokedex.igormattos.tvshowapp.data.model.MoviesResult


class MovieRepository(private val service: MovieService) : BaseRepository() {

//    private val service = RetrofitClient.getService(MovieService::class.java)


    fun getPopularList(listener: ApiListener<MoviesModel>) {
        val call = service.getPopularList()

        executeCall(call, listener)
    }

    fun getUpcomingList(listener: ApiListener<MoviesModel>) {
        val call = service.getUpcomingList()

        executeCall(call, listener)
    }

    fun getRated(listener: ApiListener<MoviesModel>) {
        val call = service.getRated()

        executeCall(call, listener)
    }

    fun getCastList(id: Int, listener: ApiListener<CastModel>) {
        val call = service.getCastList(id)

        executeCall(call, listener)
    }

    fun getMovieById(id: Int, listener: ApiListener<MoviesResult>){
        val call = service.getMovieById(id)

        executeCall(call, listener)
    }


    fun getSimilarMovies(id: Int, listener: ApiListener<MoviesModel>) {
        val call = service.getSimilarMovies(id)

        executeCall(call, listener)
    }

    fun getTrendingMovies(listener: ApiListener<MoviesModel>){
        val call = service.getTrendingMovies()

        executeCall(call, listener)
    }

    fun getSearch(name: String, listener: ApiListener<MoviesModel>){
        val call = service.getSearch(name)

        executeCall(call, listener)
    }
}
