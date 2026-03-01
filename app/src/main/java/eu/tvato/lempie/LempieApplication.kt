package eu.tvato.lempie

import android.app.Application
import android.content.Context

class LempieApplication: Application() {
    companion object{
        lateinit var appContext: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}