package eu.tvato.lempie

import android.app.Application
import eu.tvato.lempie.database.AppContainer
import eu.tvato.lempie.database.AppDataContainer

class LempieApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}