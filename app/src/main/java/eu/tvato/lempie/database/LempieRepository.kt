package eu.tvato.lempie.database

import eu.tvato.lempie.database.datetimeformat.DateTimeFormat
import eu.tvato.lempie.database.settings.Settings
import eu.tvato.lempie.database.settings.User
import eu.tvato.lempie.database.theme.Theme
import kotlinx.coroutines.flow.Flow

interface LempieRepository {
    // Settings DAO
    fun getAllUserSettings(): Flow<List<Settings>>
    fun getUserSettings(id: Int): Flow<Settings>

    suspend fun updateUserSettings(user: User)
    suspend fun insertSettings(user: User)


    // Theme DAO
    fun getThemes(): Flow<List<Theme>>
    fun getCurrentTheme(id: Int): Flow<Theme>

    suspend fun updateCurrentTheme(theme: Theme)
    suspend fun insertTheme(theme: Theme)


    // DateTimeFormat DAO
    fun getDateTimeFormats(): Flow<List<DateTimeFormat>>
    fun getCurrentDateTimeFormat(id: Int): Flow<DateTimeFormat>

    suspend fun updateCurrentDateTimeFormat(dateTimeFormat: DateTimeFormat)
    suspend fun insertDateTimeFormat(dateTimeFormat: DateTimeFormat)
}