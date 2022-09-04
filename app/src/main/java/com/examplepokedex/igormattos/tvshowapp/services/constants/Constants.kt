package com.examplepokedex.igormattos.tvshowapp.services.constants

class Constants private constructor(){

    object URL{
        const val BASE_URL = "https://api.themoviedb.org"
        const val POPULAR_POINT = "/3/movie/popular?api_key="
        const val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        const val UPCOMING_POINT = "/3/movie/upcoming?api_key="
        const val CAST = "/3/movie/{id}/credits?api_key="
    }
    object APIKEY{
        const val KEY = "PUT YOUR API KEY HERE"
    }

}