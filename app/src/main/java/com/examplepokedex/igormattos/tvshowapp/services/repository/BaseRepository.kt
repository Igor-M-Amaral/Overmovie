package com.examplepokedex.igormattos.tvshowapp.services.repository

import com.examplepokedex.igormattos.tvshowapp.services.repository.listener.ApiListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseRepository {

    fun <T> executeCall(call: Call<T>, listener: ApiListener<T>){
        call.enqueue(object : Callback<T>{
            override fun onResponse(call: Call<T>, response: Response<T>) {
                response.body()?.let {
                    listener.onSuccess(it)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                listener.onFailure(t.message.toString())
            }

        })
    }
}