package com.example.igormattos.overmovie.test

import com.example.igormattos.overmovie.data.paging.MoviePagingSource
import com.example.igormattos.overmovie.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.*

class RepositoryTest : KoinTest {

    private val repository: MovieRepositoryImpl by inject()
    private val id = 979924



    companion object {

        @BeforeClass
        @JvmStatic
        fun setup() {
            configureTestAppComponent()
        }

        @AfterClass
        fun tearDown() {
            stopKoin()
        }
    }

    @Test
    fun deve_RetornarResultadoNaoNulo_AoConectarComRepositorio() {
        runBlocking {
            val resultCast = repository.getCastList(id)

            assertNotNull(resultCast)

            val resultVideo = repository.getVideoById(id)

            assertNotNull(resultVideo)

            val resultSimilar = repository.getSimilarMovies(id)

            assertNotNull(resultSimilar)
        }
    }

}