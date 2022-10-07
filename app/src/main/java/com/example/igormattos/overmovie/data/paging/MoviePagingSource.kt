package com.example.igormattos.overmovie.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.igormattos.overmovie.data.api.MovieService
import com.example.igormattos.overmovie.data.model.MoviesResult
import com.example.igormattos.overmovie.utils.Constants
import java.lang.Exception

class PopularPagingSource(private val service: MovieService, private val filter: String) : PagingSource<Int, MoviesResult>() {
    override fun getRefreshKey(state: PagingState<Int, MoviesResult>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesResult> {

        return try {
            val currentPage = params.key ?: Constants.PAGE.DEFAULT_PAGE
            val response = service.getMovieList(filter, currentPage)
            val responseData = mutableListOf<MoviesResult>()
            val data = response.body()?.moviesResults ?: emptyList()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = if (data.isEmpty()) null else currentPage.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}