package eu.tvato.lempie.database.theme

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "theme")
data class Theme(
    @PrimaryKey(true) val id: Int = 0,
    @ColumnInfo("theme_name") val themeName: String
)
