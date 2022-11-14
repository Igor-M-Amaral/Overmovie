package com.example.igormattos.overmovie.data.repository

import androidx.paging.PagingData
import com.example.igormattos.overmovie.data.model.CastModel
import com.example.igormattos.overmovie.data.model.MovieVideo
import com.example.igormattos.overmovie.data.model.MoviesModel
import com.example.igormattos.overmovie.data.model.MoviesResult
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovieList(filter: String): Flow<PagingData<MoviesResult>>

    suspend fun getCastList(id: Int): CastModel?

    suspend fun getMovieById(id: Int): MoviesResult?

    suspend fun getSimilarMovies(id: Int): MoviesModel?

    suspend fun getVideoById(id: Int): MovieVideo?

    fun getTrendingMovies(): Flow<PagingData<MoviesResult>>

    fun getSearch(name: String): Flow<PagingData<MoviesResult>>

}