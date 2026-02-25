package eu.tvato.lempie.database.datetimeformat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "datetime_format")
data class DateTimeFormat(
    @PrimaryKey(true) val id: Int = 0,
    @ColumnInfo("datetime_format") val datetimeFormat: String,
)
