package com.example.igormattos.overmovie.data.repository

import com.example.igormattos.overmovie.data.api.MovieService
import com.example.igormattos.overmovie.data.model.CastModel
import com.example.igormattos.overmovie.data.model.MoviesModel
import com.example.igormattos.overmovie.data.model.MoviesResult


class MovieRepository(private val service: MovieService)  {

    suspend fun getMovieList(filter: String): MoviesModel? {
        val request = service.getMovieList(filter)

        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }

    suspend fun getCastList(id: Int): CastModel? {
        val request = service.getCastList(id)
        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }

    suspend fun getMovieById(id: Int): MoviesResult? {
        val request = service.getMovieById(id)

        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }


    suspend fun getSimilarMovies(id: Int): MoviesModel? {
        val request = service.getSimilarMovies(id)

        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }

    suspend fun getTrendingMovies(): MoviesModel? {
        val request = service.getTrendingMovies()

        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }

    suspend fun getSearch(name: String): MoviesModel? {
        val request = service.getSearch(name)

        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }
}
