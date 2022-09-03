package com.examplepokedex.igormattos.tvshowapp.services

import com.examplepokedex.igormattos.tvshowapp.services.constants.Constants
import com.examplepokedex.igormattos.tvshowapp.services.model.CastModel
import com.examplepokedex.igormattos.tvshowapp.services.model.MoviesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET(Constants.URL.POPULAR_POINT)
    fun getPopularList(): Call<MoviesModel>

    @GET(Constants.URL.UPCOMING_POINT)
    fun getUpcomingList(): Call<MoviesModel>

    @GET("/3/movie/{id}/credits?api_key=bbf5a3000e95f1dddf266b5e187d4b21")
    fun getCastList(@Path(value = "id", encoded = true) id: Int): Call<CastModel>
}