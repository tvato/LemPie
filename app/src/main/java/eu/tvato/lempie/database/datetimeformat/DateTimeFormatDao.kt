package eu.tvato.lempie.database.datetimeformat

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DateTimeFormatDao {
    @Query("SELECT * FROM datetime_format")
    fun getDateTimeFormats(): Flow<List<DateTimeFormat>>

    @Query("SELECT * FROM datetime_format WHERE id = :id")
    fun getCurrentDateTimeFormat(id: Int): Flow<DateTimeFormat>

    @Update
    suspend fun updateCurrentDateTimeFormat(dateTimeFormat: DateTimeFormat)

    @Insert
    suspend fun insertDateTimeFormat(dateTimeFormat: DateTimeFormat)
}