package com.examplepokedex.igormattos.tvshowapp.services.repository.listener

interface ApiListener<T> {
    fun onSuccess(result: T)
    fun onFailure(message: String)

}