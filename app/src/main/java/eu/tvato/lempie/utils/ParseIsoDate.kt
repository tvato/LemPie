package eu.tvato.lempie.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun parseIsoDate(
    isoString: String?,
    format: String = "MMM d, yy, HH:mm"
): String {
    if(isoString == null) return ""
    return Instant.parse(isoString)
        .atZone(ZoneId.systemDefault())
        .format(DateTimeFormatter.ofPattern(format))
}