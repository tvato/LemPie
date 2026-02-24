package eu.tvato.lempie.database

import android.content.Context

interface AppContainer {
    val repository: LempieRepository
}

class AppDataContainer(
    private val context: Context
): AppContainer {
    override val repository: LempieRepository by lazy {
        val db = LempieDatabase.getDatabase(context)
        OfflineLempieRepository(
            settingsDao = db.settingsDao(),
            themeDao = db.themeDao(),
            dateTimeFormatDao = db.dateTimeFormatDao()
        )
    }
}