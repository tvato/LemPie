package eu.tvato.lempie.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKeys {
    val INSTANCE = stringPreferencesKey("instance")
    val DATETIME_FORMAT = stringPreferencesKey("datetimeFormat")
}