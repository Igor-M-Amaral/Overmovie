package com.examplepokedex.igormattos.tvshowapp.services.repository

import com.examplepokedex.igormattos.tvshowapp.services.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor(){
    companion object{

        private lateinit var INSTANCE: Retrofit

        private fun getRetrofitInstance(): Retrofit{
            if (!Companion::INSTANCE.isInitialized){
                synchronized(Retrofit::class){
                    INSTANCE = Retrofit.Builder()
                        .baseUrl(Constants.URL.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
            return INSTANCE
        }

        fun <T> getService(service: Class<T>): T {
            return getRetrofitInstance().create(service)
        }
    }
}