package com.examplepokedex.igormattos.tvshowapp.services.repository

import com.examplepokedex.igormattos.tvshowapp.services.ApiListener
import com.examplepokedex.igormattos.tvshowapp.services.model.TvShowItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TvShowRepository {

    private val remote = RetrofitClient.getService(TvShowService::class.java)

    fun listTvShows(listener: ApiListener<List<TvShowItem>>) {
        val call = remote.listTvShow()
        call.enqueue(object : Callback<List<TvShowItem>>{
            override fun onResponse(call: Call<List<TvShowItem>>, response: Response<List<TvShowItem>>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        listener.onSuccess(it)
                    }
                }else{
                    listener.onFailure(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<List<TvShowItem>>, t: Throwable) {
                listener.onFailure("Erro inesperado")
            }

        })
    }
}