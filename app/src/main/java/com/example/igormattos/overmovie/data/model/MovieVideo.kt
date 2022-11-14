package com.example.igormattos.overmovie.data.model

data class MovieVideo(
    val id: Int,
    val results: List<Result>
)

data class Result(
    val key: String
)