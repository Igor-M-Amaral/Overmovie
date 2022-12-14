package com.example.igormattos.overmovie.utils.listener

import com.example.igormattos.overmovie.data.model.MovieDB

interface MovieListener {

    fun onDeleteMovie(movie: MovieDB)

    fun onListClick(id: Int)
}