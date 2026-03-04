package eu.tvato.lempie.database.selected

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SelectedDao {
    @Query("SELECT * FROM selected")
    fun getAllSelected(): Flow<List<Selected>>

    @Query("SELECT * FROM selected WHERE userId = :id")
    fun getSelectedById(id: Int): Flow<Selected>

    @Query("SELECT instance FROM selected WHERE userId = :id")
    fun getCurrentInstance(id: Int): Flow<String>

    @Query("SELECT theme_id FROM selected WHERE userId = :id")
    fun getCurrentTheme(id: Int): Flow<Int>

    @Query("SELECT datetime_format_id FROM selected WHERE userId = :id")
    fun getCurrentDateTimeFormat(id: Int): Flow<Int>

    @Update
    suspend fun updateSelected(selected: Selected)

    @Query("UPDATE selected SET instance = :instance")
    suspend fun updateInstance(instance: String)

    @Insert
    suspend fun insertSelected(selected: Selected)
}