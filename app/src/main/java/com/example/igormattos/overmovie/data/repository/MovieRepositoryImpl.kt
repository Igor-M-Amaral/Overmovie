package com.example.igormattos.overmovie.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.igormattos.overmovie.data.api.MovieService
import com.example.igormattos.overmovie.data.model.CastModel
import com.example.igormattos.overmovie.data.model.MovieVideo
import com.example.igormattos.overmovie.data.model.MoviesModel
import com.example.igormattos.overmovie.data.model.MoviesResult
import com.example.igormattos.overmovie.data.paging.MoviePagingSource
import com.example.igormattos.overmovie.data.paging.SearchPagingSource
import com.example.igormattos.overmovie.data.paging.TrendingPagingSource
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(private val service: MovieService) : MovieRepository {

    override fun getMovieList(filter: String): Flow<PagingData<MoviesResult>> {
        val request = Pager(PagingConfig(pageSize = 1)) {
            MoviePagingSource(service, filter)
        }.flow
        return request
    }

    override suspend fun getCastList(id: Int): CastModel? {
        val request = service.getCastList(id)
        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }

    override suspend fun getMovieById(id: Int): MoviesResult? {
        val request = service.getMovieById(id)

        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }


    override suspend fun getSimilarMovies(id: Int): MoviesModel? {
        val request = service.getSimilarMovies(id)

        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }

    override suspend fun getVideoById(id: Int): MovieVideo? {
        val request = service.getVideoById(id)

        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }

    override fun getTrendingMovies(): Flow<PagingData<MoviesResult>> {
        val request = Pager(PagingConfig(pageSize = 1)) {
            TrendingPagingSource(service)
        }.flow
        return request
    }

    override fun getSearch(name: String): Flow<PagingData<MoviesResult>> {
        val request = Pager(PagingConfig(pageSize = 1)) {
            SearchPagingSource(service, name)
        }.flow
        return request
    }
}