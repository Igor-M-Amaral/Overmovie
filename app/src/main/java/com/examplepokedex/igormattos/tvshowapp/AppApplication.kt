package com.examplepokedex.igormattos.tvshowapp

import android.app.Application
import com.examplepokedex.igormattos.tvshowapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@AppApplication)
            modules(viewModelModule)
            modules(overViewModule)
            modules(favoriteViewModule)
            modules(repositoryModule)
            modules(daoModule)
        }
    }
}