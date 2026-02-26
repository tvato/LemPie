package eu.tvato.lempie.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun parseIsoDate(
    isoString: String?,
): String {
    val format = CurrentSettings.getFormat()
    if(isoString == null) return ""
    return Instant.parse(isoString)
        .atZone(ZoneId.systemDefault())
        .format(DateTimeFormatter.ofPattern(format))
}