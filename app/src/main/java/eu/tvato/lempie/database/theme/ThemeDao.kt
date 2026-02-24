package eu.tvato.lempie.database.theme

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ThemeDao {
    @Query("SELECT * FROM theme")
    fun getThemes(): Flow<List<Theme>>

    @Query("SELECT * FROM theme WHERE id = :id")
    fun getCurrentTheme(id: Int): Flow<Theme>

    @Update
    suspend fun updateCurrentTheme(theme: Theme)

    @Insert
    suspend fun insertTheme(theme: Theme)
}