package eu.tvato.lempie.database.settings

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import eu.tvato.lempie.database.datetimeformat.DateTimeFormat
import eu.tvato.lempie.database.theme.Theme

@Entity(
    tableName = "user",
    foreignKeys = [
        ForeignKey(
            entity = Theme::class,
            parentColumns = ["id"],
            childColumns = ["theme_id"]
        ),
        ForeignKey(
            entity = DateTimeFormat::class,
            parentColumns = ["id"],
            childColumns = ["datetime_format_id"]
        )
    ],
    indices = [
        Index("theme_id"),
        Index("datetime_format_id")
    ]
)
data class User(
    @PrimaryKey(true) val userId: Int = 0,
    @ColumnInfo("theme_id") val themeId: Int,
    @ColumnInfo("datetime_format_id") val datetimeFormatId: Int,
)