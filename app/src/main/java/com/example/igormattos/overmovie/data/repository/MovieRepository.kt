package com.example.igormattos.overmovie.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.igormattos.overmovie.data.api.MovieService
import com.example.igormattos.overmovie.data.model.CastModel
import com.example.igormattos.overmovie.data.model.MoviesModel
import com.example.igormattos.overmovie.data.model.MoviesResult
import com.example.igormattos.overmovie.data.paging.PopularPagingSource
import com.example.igormattos.overmovie.data.paging.SearchPagingSource
import com.example.igormattos.overmovie.data.paging.TrendingPagingSource
import kotlinx.coroutines.flow.Flow


class MovieRepository(private val service: MovieService)  {

    fun getMovieList(filter: String): Flow<PagingData<MoviesResult>> {
        val request = Pager(PagingConfig(pageSize = 1)){
            PopularPagingSource(service, filter)
        }.flow
        return request
    }

    suspend fun getCastList(id: Int): CastModel? {
        val request = service.getCastList(id)
        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }

    suspend fun getMovieById(id: Int): MoviesResult? {
        val request = service.getMovieById(id)

        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }


    suspend fun getSimilarMovies(id: Int): MoviesModel? {
        val request = service.getSimilarMovies(id)

        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }

    fun getTrendingMovies(): Flow<PagingData<MoviesResult>> {
        val request = Pager(PagingConfig(pageSize = 1)){
            TrendingPagingSource(service)
        }.flow
        return request
    }

    fun getSearch(name: String): Flow<PagingData<MoviesResult>> {
        val request = Pager(PagingConfig(pageSize = 1)){
            SearchPagingSource(service, name)
        }.flow
        return request
    }
}
