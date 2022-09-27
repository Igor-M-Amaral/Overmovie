package com.example.igormattos.overmovie.ui.di

import com.example.igormattos.overmovie.ui.viewmodel.FavoritesViewModel
import com.example.igormattos.overmovie.ui.viewmodel.MovieListViewModel
import com.example.igormattos.overmovie.ui.viewmodel.OverViewViewModel
import com.example.igormattos.overmovie.data.local.FavoriteDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    fun load() {
        loadKoinModules( movieListModule() + overviewModule() + favoriteModule() + daoModule())
    }

    private fun movieListModule(): Module {
        return module {
            factory { MovieListViewModel(get()) }
        }
    }


    private fun overviewModule(): Module {
        return module {
            factory { OverViewViewModel(get(), get()) }
        }
    }

    private fun favoriteModule(): Module {
        return module {
            factory { FavoritesViewModel(get()) }
        }
    }

    private fun daoModule(): Module {
        return module {
            single { FavoriteDatabase.getDataBase(androidContext()).getFavoriteDao() }
        }
    }
}