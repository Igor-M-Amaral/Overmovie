package com.examplepokedex.igormattos.tvshowapp.services

import com.examplepokedex.igormattos.tvshowapp.services.constants.Constants
import com.examplepokedex.igormattos.tvshowapp.services.model.MoviesModel
import retrofit2.Call
import retrofit2.http.GET

interface MovieService {

    @GET(Constants.URL.POPULAR_POINT)
    fun getPopularList(): Call<MoviesModel>

    @GET(Constants.URL.UPCOMING_POINT)
    fun getUpcomingList(): Call<MoviesModel>
}