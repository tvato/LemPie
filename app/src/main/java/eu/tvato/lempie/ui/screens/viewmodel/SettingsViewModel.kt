package eu.tvato.lempie.ui.screens.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eu.tvato.lempie.datastore.DataStoreRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    context: Context
): ViewModel() {
    val dataStore = DataStoreRepository(context)

    val datetimeFormat = dataStore.getDatetimeFormat().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = ""
    )

    val theme = dataStore.getTheme().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = ""
    )

    val instance = dataStore.getInstance().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = ""
    )

    fun setInstance(newInstance: String){
        viewModelScope.launch {
            dataStore.setInstance(newInstance)
        }
    }

    fun setTheme(newTheme: String){
        viewModelScope.launch {
            dataStore.setTheme(newTheme)
        }
    }

    fun setDatetimeFormat(newFormat: String){
        viewModelScope.launch {
            dataStore.setDatetimeFormat(newFormat)
        }
    }

    class MainViewModelFactory(
        private val context: Context
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SettingsViewModel(context = context) as T
        }
    }
}