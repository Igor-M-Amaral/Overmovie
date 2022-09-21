package com.example.igormattos.overmovie.utils.listener

interface ApiListener<T> {
    fun onSuccess(result: T)
    fun onFailure(message: String)

}