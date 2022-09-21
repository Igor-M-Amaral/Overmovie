package com.examplepokedex.igormattos.tvshowapp

import android.app.Application
import com.examplepokedex.igormattos.tvshowapp.data.di.*
import com.examplepokedex.igormattos.tvshowapp.ui.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
        }

        PresentationModule.load()
        DataModule.load()
    }
}