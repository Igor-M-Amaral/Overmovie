package com.examplepokedex.igormattos.tvshowapp

import android.app.Application
import com.examplepokedex.igormattos.tvshowapp.di.daoModule
import com.examplepokedex.igormattos.tvshowapp.di.overViewModule
import com.examplepokedex.igormattos.tvshowapp.di.repositoryModule
import com.examplepokedex.igormattos.tvshowapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@AppApplication)
            modules(viewModelModule)
            modules(repositoryModule)
            modules(overViewModule)
            modules(daoModule)
        }
    }
}