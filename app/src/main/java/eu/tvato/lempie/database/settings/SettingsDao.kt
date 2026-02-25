package eu.tvato.lempie.database.settings

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {
    @Transaction
    @Query("SELECT * FROM Selected")
    fun getAllUserSettings(): Flow<List<Settings>>

    @Transaction
    @Query("SELECT * FROM Selected WHERE userId = :id")
    fun getUserSettings(id: Int): Flow<Settings>
}