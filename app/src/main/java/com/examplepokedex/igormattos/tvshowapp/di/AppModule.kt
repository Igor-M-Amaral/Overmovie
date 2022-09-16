package com.examplepokedex.igormattos.tvshowapp.di

import com.examplepokedex.igormattos.tvshowapp.services.repository.MovieRepository
import com.examplepokedex.igormattos.tvshowapp.services.repository.local.FavoriteDatabase
import com.examplepokedex.igormattos.tvshowapp.viewmodel.MovieListViewModel
import com.examplepokedex.igormattos.tvshowapp.viewmodel.OverViewViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { MovieListViewModel(get()) }
}

val overViewModule = module {
    viewModel { OverViewViewModel(get()) }
}

val repositoryModule = module {
    single { MovieRepository() }
}

val daoModule = module {
    single { FavoriteDatabase.getDataBase(androidContext()).getFavoriteDao() }
}