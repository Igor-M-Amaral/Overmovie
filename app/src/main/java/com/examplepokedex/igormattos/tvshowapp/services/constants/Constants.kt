package com.examplepokedex.igormattos.tvshowapp.services.constants

class Constants private constructor(){

    object URL{
        const val BASE_URL = "https://api.themoviedb.org"
        const val POPULAR_POINT = "/3/movie/popular?api_key=bbf5a3000e95f1dddf266b5e187d4b21"
        const val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        const val UPCOMING_POINT = "/3/movie/upcoming?api_key=bbf5a3000e95f1dddf266b5e187d4b21"
//        const val CAST = "/3/movie/634649/credits?api_key=bbf5a3000e95f1dddf266b5e187d4b21"
    }
}