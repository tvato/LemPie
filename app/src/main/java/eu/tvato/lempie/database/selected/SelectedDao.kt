package eu.tvato.lempie.database.selected

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface SelectedDao {
    @Query("SELECT * FROM selected")
    fun getAllSelected(): Flow<List<Selected>>

    @Query("SELECT * FROM selected WHERE userId = :id")
    fun getSelectedById(id: Int): Flow<Selected>

    @Update
    suspend fun updateSelected(selected: Selected)

    @Transaction
    @Insert
    suspend fun insertSelected(selected: Selected)
}