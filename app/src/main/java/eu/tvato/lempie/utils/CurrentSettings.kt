package eu.tvato.lempie.utils

import android.content.Context
import eu.tvato.lempie.database.LempieDatabase
import eu.tvato.lempie.database.settings.Settings
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first

object CurrentSettings {
    private var settings: Settings? = null

    suspend fun initialize(userId: Int, context: Context){
        val db = LempieDatabase.getDatabase(context)
        settings = db.settingsDao().getUserSettings(userId).filterNotNull().first()
    }

    fun getFormat() = settings?.dateTimeFormat?.format
    fun getTheme() = settings?.theme
}