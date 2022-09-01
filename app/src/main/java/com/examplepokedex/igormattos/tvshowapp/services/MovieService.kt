package com.examplepokedex.igormattos.tvshowapp.services

import com.examplepokedex.igormattos.tvshowapp.services.constants.Constants
import com.examplepokedex.igormattos.tvshowapp.services.model.PopularResponse
import com.examplepokedex.igormattos.tvshowapp.services.model.UpcomingResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieService {

    @GET(Constants.URL.POPULAR_POINT)
    fun getPopularList(): Call<PopularResponse>

    @GET(Constants.URL.UPCOMING_POINT)
    fun getUpcomingList(): Call<UpcomingResponse>
}