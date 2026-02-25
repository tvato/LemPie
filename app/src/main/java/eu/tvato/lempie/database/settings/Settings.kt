package eu.tvato.lempie.database.settings

import androidx.room.Embedded
import androidx.room.Relation
import eu.tvato.lempie.database.datetimeformat.DateTimeFormat
import eu.tvato.lempie.database.theme.Theme
import eu.tvato.lempie.database.selected.Selected

data class Settings(
    @Embedded val selected: Selected,
    @Relation(
        parentColumn = "theme_id",
        entityColumn = "id"
    ) val theme: Theme,
    @Relation(
        parentColumn = "datetime_format_id",
        entityColumn = "id"
    ) val dateTimeFormat: DateTimeFormat
)