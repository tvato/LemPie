package eu.tvato.lempie.database.settings

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {
    @Transaction
    @Query("SELECT * FROM user")
    fun getAllUserSettings(): Flow<List<Settings>>

    @Transaction
    @Query("SELECT * FROM user WHERE userId = :id")
    fun getUserSettings(id: Int): Flow<Settings>

    @Update
    suspend fun updateUserSettings(settings: User)

    @Transaction
    @Insert
    suspend fun insertSettings(settings: User)
}