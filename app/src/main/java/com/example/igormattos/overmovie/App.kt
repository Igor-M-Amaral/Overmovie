package com.example.igormattos.overmovie

import android.app.Application
import com.example.igormattos.overmovie.data.di.*
import com.example.igormattos.overmovie.ui.di.PresentationModule
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