package eu.tvato.lempie.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import eu.tvato.lempie.database.datetimeformat.DateTimeFormat
import eu.tvato.lempie.database.datetimeformat.DateTimeFormatDao
import eu.tvato.lempie.database.settings.Settings
import eu.tvato.lempie.database.settings.SettingsDao
import eu.tvato.lempie.database.settings.User
import eu.tvato.lempie.database.theme.Theme
import eu.tvato.lempie.database.theme.ThemeDao

@Database(
    entities = [Theme::class, DateTimeFormat::class, User::class],
    version = 1,
    exportSchema = false
)
abstract class LempieDatabase: RoomDatabase() {
    abstract fun settingsDao(): SettingsDao
    abstract fun themeDao(): ThemeDao
    abstract fun dateTimeFormatDao(): DateTimeFormatDao

    companion object{
        @Volatile
        private var Instance: LempieDatabase? = null

        fun getDatabase(context: Context): LempieDatabase{
            return Instance ?: synchronized(this){
                Room.databaseBuilder(context, LempieDatabase::class.java, "lempie_database")
                    .createFromAsset("defaults.db")
                    .fallbackToDestructiveMigration(false)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}