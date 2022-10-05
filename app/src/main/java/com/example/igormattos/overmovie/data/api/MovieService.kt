package com.example.igormattos.overmovie.data.api

import com.example.igormattos.overmovie.utils.Constants
import com.example.igormattos.overmovie.data.model.CastModel
import com.example.igormattos.overmovie.data.model.MoviesModel
import com.example.igormattos.overmovie.data.model.MoviesResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(Constants.URL.TYPE_POINT + Constants.APIKEY.KEY)
    suspend fun getMovieList(
        @Path(value = "type") type: String,
        @Query("page") page: Int = 1
    ): Response<MoviesModel>

    @GET(Constants.URL.TRENDING + Constants.APIKEY.KEY)
    suspend fun getTrendingMovies(): Response<MoviesModel>

    @GET(Constants.URL.DETAILS + Constants.APIKEY.KEY)
    suspend fun getMovieById(@Path(value = "movie_id", encoded = true) movie_id: Int): Response<MoviesResult>

    @GET(Constants.URL.CAST + Constants.APIKEY.KEY)
    suspend fun getCastList(@Path(value = "id", encoded = true) id: Int): Response<CastModel>

    @GET(Constants.URL.SIMILAR + Constants.APIKEY.KEY)
    suspend fun getSimilarMovies(@Path(value = "id", encoded = true) id: Int): Response<MoviesModel>

    @GET(Constants.URL.SEARCH + Constants.APIKEY.KEY)
    suspend fun getSearch(@Query("query") name: String): Response<MoviesModel>

}
