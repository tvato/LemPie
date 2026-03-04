package eu.tvato.lempie.utils

import android.content.Context
import android.util.Log
import eu.tvato.lempie.database.LempieDatabase
import eu.tvato.lempie.database.settings.Settings
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first


object CurrentSettings {
    private var settings: Settings? = null
    private var userId: Int = 0

    suspend fun initialize(userId: Int, context: Context){
        val db = LempieDatabase.getDatabase(context)
        settings = db.settingsDao().getUserSettings(userId).filterNotNull().first()
        this.userId = userId
    }

    suspend fun saveChanges(context: Context){
        val db = LempieDatabase.getDatabase(context)
        val dbSettings = db.settingsDao().getUserSettings(userId).filterNotNull().first()

        if(settings != dbSettings && settings != null){
            db.selectedDao().updateSelected(settings!!.selected)
            db.themeDao().updateTheme(settings!!.theme)
            db.dateTimeFormatDao().updateDateTimeFormat(settings!!.dateTimeFormat)
        }
    }

    fun getFormat() = settings?.dateTimeFormat?.format
    fun getTheme() = settings?.theme
    fun getInstance() = settings?.selected?.instanceUrl

    fun setInstance(newInstance: String?){
        Log.d("dd", "Setting instance to: $newInstance")
        if(newInstance == null) return
        settings?.selected?.instanceUrl = newInstance
    }

    fun setFormatId(newId: Int){
        settings?.selected?.datetimeFormatId = newId
    }

    fun setThemeId(newId: Int){
        settings?.selected?.themeId = newId
    }
}