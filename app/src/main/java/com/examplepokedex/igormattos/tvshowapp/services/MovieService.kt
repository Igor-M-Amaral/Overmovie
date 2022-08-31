package com.examplepokedex.igormattos.tvshowapp.services

import com.examplepokedex.igormattos.tvshowapp.services.constants.Constants
import com.examplepokedex.igormattos.tvshowapp.services.model.Movie
import com.examplepokedex.igormattos.tvshowapp.services.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieService {

    @GET(Constants.URL.END_POINT)
    fun getMovieList(): Call<MovieResponse>
}