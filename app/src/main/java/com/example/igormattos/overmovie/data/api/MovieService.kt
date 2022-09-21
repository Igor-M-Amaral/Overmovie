package com.example.igormattos.overmovie.data.api

import com.example.igormattos.overmovie.utils.Constants
import com.example.igormattos.overmovie.data.model.CastModel
import com.example.igormattos.overmovie.data.model.MoviesModel
import com.example.igormattos.overmovie.data.model.MoviesResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(Constants.URL.TYPE_POINT + Constants.APIKEY.KEY)
    fun getMovieList(@Path(value = "type") type: String): Call<MoviesModel>

    @GET(Constants.URL.TRENDING + Constants.APIKEY.KEY)
    fun getTrendingMovies(): Call<MoviesModel>

    @GET(Constants.URL.DETAILS + Constants.APIKEY.KEY)
    fun getMovieById(@Path(value = "movie_id", encoded = true) movie_id: Int): Call<MoviesResult>

    @GET(Constants.URL.CAST + Constants.APIKEY.KEY)
    fun getCastList(@Path(value = "id", encoded = true) id: Int): Call<CastModel>

    @GET(Constants.URL.SIMILAR + Constants.APIKEY.KEY)
    fun getSimilarMovies(@Path(value = "id", encoded = true) id: Int): Call<MoviesModel>

    @GET(Constants.URL.SEARCH + Constants.APIKEY.KEY)
    fun getSearch(@Query("query") name: String): Call<MoviesModel>

}
