package com.example.igormattos.overmovie.data.di

import android.util.Log
import com.example.igormattos.overmovie.data.api.MovieService
import com.example.igormattos.overmovie.utils.Constants
import com.example.igormattos.overmovie.data.repository.MovieRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    private const val OK_HTTP= "Ok http"

    fun load() {
        loadKoinModules(movieModule() + networkModule())
    }

    private fun movieModule(): Module {
        return module {
            single { MovieRepository(get()) }

        }
    }

    private fun networkModule(): Module {
        return module {

            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.e(OK_HTTP, it )
                }

                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            single {
                Retrofit.Builder()
                    .baseUrl(Constants.URL.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MovieService::class.java)
            }

        }
    }
}