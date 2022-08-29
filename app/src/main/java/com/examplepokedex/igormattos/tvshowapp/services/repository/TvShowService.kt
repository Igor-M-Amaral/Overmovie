package com.examplepokedex.igormattos.tvshowapp.services.repository

import com.examplepokedex.igormattos.tvshowapp.services.constants.Constants
import com.examplepokedex.igormattos.tvshowapp.services.model.TvShowItem
import retrofit2.Call
import retrofit2.http.GET

interface TvShowService {

    @GET(Constants.URL.END_POINT)
    fun listTvShow(): Call<List<TvShowItem>>


}