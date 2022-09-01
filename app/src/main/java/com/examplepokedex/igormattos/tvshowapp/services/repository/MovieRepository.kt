package com.examplepokedex.igormattos.tvshowapp.services.repository

import com.examplepokedex.igormattos.tvshowapp.services.ApiListener
import com.examplepokedex.igormattos.tvshowapp.services.MovieService
import com.examplepokedex.igormattos.tvshowapp.services.model.PopularResponse
import com.examplepokedex.igormattos.tvshowapp.services.model.UpcomingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {

    private val remote = RetrofitClient.getService(MovieService::class.java)

    fun getPopularList(listener: ApiListener<PopularResponse>){
        val call = remote.getPopularList()
        call.enqueue(object : Callback<PopularResponse>{
            override fun onResponse(call: Call<PopularResponse>, response: Response<PopularResponse>) {
                if (response.code() == 200){
                    listener.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<PopularResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getUpcomingList(listener: ApiListener<UpcomingResponse>){
        val call = remote.getUpcomingList()
        call.enqueue(object : Callback<UpcomingResponse>{
            override fun onResponse(
                call: Call<UpcomingResponse>,
                response: Response<UpcomingResponse>
            ) {
                listener.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<UpcomingResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}