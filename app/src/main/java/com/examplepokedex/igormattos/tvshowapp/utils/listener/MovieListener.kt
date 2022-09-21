package com.examplepokedex.igormattos.tvshowapp.utils.listener

import com.examplepokedex.igormattos.tvshowapp.data.model.MovieDB

interface MovieListener {

    fun onDeleteById(movie: MovieDB)

    fun onListClick(id: Int)
}