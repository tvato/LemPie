package eu.tvato.lempie.database

import eu.tvato.lempie.database.datetimeformat.DateTimeFormat
import eu.tvato.lempie.database.datetimeformat.DateTimeFormatDao
import eu.tvato.lempie.database.settings.Settings
import eu.tvato.lempie.database.settings.SettingsDao
import eu.tvato.lempie.database.settings.User
import eu.tvato.lempie.database.theme.Theme
import eu.tvato.lempie.database.theme.ThemeDao
import kotlinx.coroutines.flow.Flow

class OfflineLempieRepository(
    private val settingsDao: SettingsDao,
    private val themeDao: ThemeDao,
    private val dateTimeFormatDao: DateTimeFormatDao
): LempieRepository {
    override fun getAllUserSettings(): Flow<List<Settings>> = settingsDao.getAllUserSettings()
    override fun getUserSettings(id: Int): Flow<Settings> = settingsDao.getUserSettings(id)

    override suspend fun updateUserSettings(user: User) = settingsDao.updateUserSettings(user)
    override suspend fun insertSettings(user: User) = settingsDao.insertSettings(user)


    override fun getThemes(): Flow<List<Theme>> = themeDao.getThemes()
    override fun getCurrentTheme(id: Int): Flow<Theme> = themeDao.getCurrentTheme(id)

    override suspend fun updateCurrentTheme(theme: Theme) = themeDao.updateCurrentTheme(theme)
    override suspend fun insertTheme(theme: Theme) = themeDao.insertTheme(theme)


    override fun getDateTimeFormats(): Flow<List<DateTimeFormat>> = dateTimeFormatDao.getDateTimeFormats()
    override fun getCurrentDateTimeFormat(id: Int): Flow<DateTimeFormat> = dateTimeFormatDao.getCurrentDateTimeFormat(id)

    override suspend fun updateCurrentDateTimeFormat(dateTimeFormat: DateTimeFormat) = dateTimeFormatDao.updateCurrentDateTimeFormat(dateTimeFormat)
    override suspend fun insertDateTimeFormat(dateTimeFormat: DateTimeFormat) = dateTimeFormatDao.insertDateTimeFormat(dateTimeFormat)

}