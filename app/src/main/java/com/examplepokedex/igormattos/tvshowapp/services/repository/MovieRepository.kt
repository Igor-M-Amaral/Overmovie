package com.examplepokedex.igormattos.tvshowapp.services.repository

import com.examplepokedex.igormattos.tvshowapp.services.ApiListener
import com.examplepokedex.igormattos.tvshowapp.services.MovieService
import com.examplepokedex.igormattos.tvshowapp.services.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {

    private val remote = RetrofitClient.getService(MovieService::class.java)

    fun getMovieList(listener: ApiListener<MovieResponse>){
        val call = remote.getMovieList()
        call.enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.code() == 200){
                    listener.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}