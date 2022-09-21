package com.examplepokedex.igormattos.tvshowapp.utils

class Constants private constructor(){

    object URL{
        const val BASE_URL = "https://api.themoviedb.org"
        const val POPULAR_POINT = "/3/movie/popular?api_key="
        const val UPCOMING_POINT = "/3/movie/upcoming?api_key="
        const val TRENDING = "/3/trending/movie/day?api_key="
        const val RATED = "/3/movie/top_rated?api_key="
        const val CAST = "/3/movie/{id}/credits?api_key="
        const val SIMILAR = "/3/movie/{id}/similar?api_key="
        const val DETAILS = "/3/movie/{movie_id}?api_key="
        const val SEARCH = "/3/search/movie?api_key="

        const val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
    }
    object APIKEY{
        const val KEY = "38e916798d955f2ece239fec2cb84d59"
    }

    object BUNDLE {
        const val MOVIEFILTER = "moviefilter"
    }

    object FILTER {
        const val UPCOMING = 0
        const val POPULAR = 1
        const val TRENDING = 2
        const val RATED = 3
    }

    object TABLE{
        const val NAME = "FAVORITE"
    }
}

