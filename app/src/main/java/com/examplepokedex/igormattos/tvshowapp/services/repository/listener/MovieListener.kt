package com.examplepokedex.igormattos.tvshowapp.services.repository.listener

import com.examplepokedex.igormattos.tvshowapp.services.model.MovieDB

interface MovieListener {

    fun onDeleteById(movie: MovieDB)

    fun onListClick(id: Int)
}