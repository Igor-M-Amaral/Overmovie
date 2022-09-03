package com.examplepokedex.igormattos.tvshowapp.services.repository

import com.examplepokedex.igormattos.tvshowapp.services.ApiListener
import com.examplepokedex.igormattos.tvshowapp.services.MovieService
import com.examplepokedex.igormattos.tvshowapp.services.model.CastModel
import com.examplepokedex.igormattos.tvshowapp.services.model.MoviesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {

    private val remote = RetrofitClient.getService(MovieService::class.java)

    fun getPopularList(listener: ApiListener<MoviesModel>){
        val call = remote.getPopularList()
        call.enqueue(object : Callback<MoviesModel>{
            override fun onResponse(call: Call<MoviesModel>, response: Response<MoviesModel>) {
                if (response.code() == 200){
                    listener.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<MoviesModel>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getUpcomingList(listener: ApiListener<MoviesModel>){
        val call = remote.getUpcomingList()
        call.enqueue(object : Callback<MoviesModel>{
            override fun onResponse(
                call: Call<MoviesModel>,
                response: Response<MoviesModel>
            ) {
                listener.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<MoviesModel>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getCastList(id: Int, listener: ApiListener<CastModel>){
        val call = remote.getCastList(id)
        call.enqueue(object : Callback<CastModel>{
            override fun onResponse(call: Call<CastModel>, response: Response<CastModel>) {
                listener.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<CastModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}