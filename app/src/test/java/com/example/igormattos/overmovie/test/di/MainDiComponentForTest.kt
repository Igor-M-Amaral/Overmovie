package com.example.igormattos.overmovie.test

import com.example.igormattos.overmovie.utils.Constants
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin


fun configureTestAppComponent() = startKoin {
    loadKoinModules(
        configureDataModuleForTest(Constants.URL.BASE_URL)
    )
}