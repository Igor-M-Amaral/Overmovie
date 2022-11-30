package com.example.igormattos.overmovie.test

import com.example.igormattos.overmovie.data.api.MovieService
import com.example.igormattos.overmovie.data.paging.MoviePagingSource
import com.example.igormattos.overmovie.utils.Constants
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class MovieServiceTest {
    private var mockWebServer = MockWebServer()
    lateinit var service: MovieService

    @Before
    fun createService() {
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieService::class.java)

    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun deve_acessarACategoriaCorreta_AoReceberOParametro() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody("{}"))
            service.getMovieList("upcoming")
            val requestOptionUpcoming = mockWebServer.takeRequest()
            assertEquals(
                requestOptionUpcoming.path,
                "/3/movie/upcoming?api_key=" + Constants.APIKEY.KEY + "&page=1"
            )


            mockWebServer.enqueue(MockResponse().setBody("{}"))
            service.getMovieList("popular", 2)
            val requestOptionPopular = mockWebServer.takeRequest()
            assertEquals(
                requestOptionPopular.path,
                "/3/movie/popular?api_key=" + Constants.APIKEY.KEY + "&page=2"
            )

            mockWebServer.enqueue(MockResponse().setBody("{}"))
            service.getMovieList("top_rated", 3)
            val requestOptionTopRated = mockWebServer.takeRequest()
            assertEquals(
                requestOptionTopRated.path,
                "/3/movie/top_rated?api_key=" + Constants.APIKEY.KEY + "&page=3"
            )

            mockWebServer.enqueue(MockResponse().setBody("{}"))
            service.getTrendingMovies(4)
            val requestOptionTrending = mockWebServer.takeRequest()
            assertEquals(
                requestOptionTrending.path,
                "/3/trending/movie/day?api_key=" + Constants.APIKEY.KEY + "&page=4"
            )
        }
    }

    @Test
    fun deve_AlcancarOEndpointCorreto_AoReceberAQuery() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setBody("{}"))
            service.getSearch("batman")
            val request = mockWebServer.takeRequest()
            println(request.path)
            assertEquals(
                request.path,
                "/3/search/movie?api_key=" + Constants.APIKEY.KEY +"&query=batman&page=1"
            )
        }
    }
}