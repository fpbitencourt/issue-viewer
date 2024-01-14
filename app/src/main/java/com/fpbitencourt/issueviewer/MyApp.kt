package com.fpbitencourt.issueviewer

import android.app.Application
import com.fpbitencourt.issueviewer.di.appModule
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}