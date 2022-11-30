package com.example.igormattos.overmovie.test

import com.example.igormattos.overmovie.data.api.MovieService
import com.example.igormattos.overmovie.data.repository.MovieRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Esse método cria os módulos da camada de dados para teste
 */
fun configureDataModuleForTest(baseUrl: String) = module {
    single<MovieService> {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieService::class.java)

    }
    single { MovieRepositoryImpl(get()) }

}
