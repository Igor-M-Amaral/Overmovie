package com.examplepokedex.igormattos.tvshowapp.services

import com.examplepokedex.igormattos.tvshowapp.services.constants.Constants
import com.examplepokedex.igormattos.tvshowapp.services.model.CastModel
import com.examplepokedex.igormattos.tvshowapp.services.model.MoviesModel
import com.examplepokedex.igormattos.tvshowapp.services.model.MoviesResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(Constants.URL.POPULAR_POINT + Constants.APIKEY.KEY)
    fun getPopularList(): Call<MoviesModel>

    @GET(Constants.URL.UPCOMING_POINT + Constants.APIKEY.KEY)
    fun getUpcomingList(): Call<MoviesModel>

    @GET(Constants.URL.TRENDING + Constants.APIKEY.KEY)
    fun getTrendingMovies(): Call<MoviesModel>

    @GET(Constants.URL.RATED + Constants.APIKEY.KEY)
    fun getRated(): Call<MoviesModel>

    @GET(Constants.URL.DETAILS + Constants.APIKEY.KEY)
    fun getMovieById(@Path(value = "movie_id", encoded = true) movie_id: Int) : Call<MoviesResult>

    @GET(Constants.URL.CAST + Constants.APIKEY.KEY)
    fun getCastList(@Path(value = "id", encoded = true) id: Int): Call<CastModel>

    @GET(Constants.URL.SIMILAR + Constants.APIKEY.KEY)
    fun getSimilarMovies(@Path(value = "id", encoded = true) id: Int): Call<MoviesModel>

    @GET(Constants.URL.SEARCH + Constants.APIKEY.KEY)
    fun getSearch(@Query("query") name: String): Call<MoviesModel>

}
