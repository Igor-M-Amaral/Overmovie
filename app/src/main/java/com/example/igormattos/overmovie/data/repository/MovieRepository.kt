package com.example.igormattos.overmovie.data.repository

import com.example.igormattos.overmovie.utils.listener.ApiListener
import com.example.igormattos.overmovie.data.api.MovieService
import com.example.igormattos.overmovie.data.model.CastModel
import com.example.igormattos.overmovie.data.model.MoviesModel
import com.example.igormattos.overmovie.data.model.MoviesResult


class MovieRepository(private val service: MovieService) : BaseRepository() {

    fun getMovieList(listener: ApiListener<MoviesModel>, filter: String) {
        val call = service.getMovieList(filter)

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
