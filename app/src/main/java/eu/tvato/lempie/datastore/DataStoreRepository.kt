package eu.tvato.lempie.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import eu.tvato.lempie.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class DataStoreRepository(context: Context) {
    private val dataStore: DataStore<Preferences> = context.dataStore

    fun getInstance(): Flow<String> = dataStore.data.map {
        it[DataStoreKeys.INSTANCE] ?: Constants.BASE_URL
    }

    fun getDatetimeFormat(): Flow<String> = dataStore.data.map {
        it[DataStoreKeys.DATETIME_FORMAT] ?: "MMM d, yy, HH:mm"
    }

    fun getTheme(): Flow<String> = dataStore.data.map {
        it[DataStoreKeys.THEME] ?: "Dark"
    }

    suspend fun setInstance(newInstance: String){
        dataStore.edit {
            it[DataStoreKeys.INSTANCE] = newInstance
        }
    }

    suspend fun setDatetimeFormat(newFormat: String){
        dataStore.edit {
            it[DataStoreKeys.DATETIME_FORMAT] = newFormat
        }
    }

    suspend fun setTheme(newTheme: String){
        dataStore.edit {
            it[DataStoreKeys.THEME] = newTheme
        }
    }
}