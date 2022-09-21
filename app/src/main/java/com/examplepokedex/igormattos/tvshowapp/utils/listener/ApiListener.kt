package com.examplepokedex.igormattos.tvshowapp.utils.listener

interface ApiListener<T> {
    fun onSuccess(result: T)
    fun onFailure(message: String)

}