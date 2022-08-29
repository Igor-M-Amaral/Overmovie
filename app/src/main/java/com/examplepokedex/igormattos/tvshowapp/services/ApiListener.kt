package com.examplepokedex.igormattos.tvshowapp.services

interface ApiListener<T> {
    fun onSuccess(result: T)
    fun onFailure(message: String)

}