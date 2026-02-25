package eu.tvato.lempie.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tvato.lempie.database.datetimeformat.DateTimeFormat
import eu.tvato.lempie.database.datetimeformat.DateTimeFormatDao
import eu.tvato.lempie.database.settings.Settings
import eu.tvato.lempie.database.settings.SettingsDao
import eu.tvato.lempie.database.selected.Selected
import eu.tvato.lempie.database.theme.Theme
import eu.tvato.lempie.database.theme.ThemeDao
import eu.tvato.lempie.database.selected.SelectedDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class DatabaseViewModel(
    db: LempieDatabase
): ViewModel() {
    private val settingsDao: SettingsDao = db.settingsDao()
    private val themeDao: ThemeDao = db.themeDao()
    private val dateTimeFormatDao: DateTimeFormatDao = db.dateTimeFormatDao()
    private val selectedDao: SelectedDao = db.selectedDao()
    private val userId = MutableStateFlow(1)
    private val themeId = MutableStateFlow(1)
    private val formatId = MutableStateFlow(1)

    private val _settings = MutableStateFlow<Settings?>(null)
    val userSettings: StateFlow<Settings?> = _settings.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val theme: Flow<Theme> = themeId.filterNotNull().flatMapLatest { id ->
        themeDao.getCurrentTheme(id)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val dateTimeFormat: Flow<DateTimeFormat> = formatId.filterNotNull().flatMapLatest { id ->
        dateTimeFormatDao.getCurrentDateTimeFormat(id)
    }



    fun loadUserSettings(){
        viewModelScope.launch {
            _settings.value = settingsDao.getUserSettings(userId.value).filterNotNull().first()
        }
    }

    fun addTheme(name: String){
        viewModelScope.launch {
            themeDao.insertTheme(Theme(
                themeName = "",  primary = 0, onPrimary = 0, primaryContainer = 0, onPrimaryContainer = 0,
                inversePrimary = 0, secondary = 0, onSecondary = 0, secondaryContainer = 0, onSecondaryContainer = 0,
                tertiary = 0, onTertiary = 0, tertiaryContainer = 0, onTertiaryContainer = 0, background = 0,
                onBackground = 0, surface = 0, onSurface = 0, surfaceVariant = 0, onSurfaceVariant = 0,
                surfaceTint = 0, inverseSurface = 0, inverseOnSurface = 0, error = 0, onError = 0,
                errorContainer = 0, onErrorContainer = 0, outline = 0, outlineVariant = 0, scrim = 0,
                surfaceBright = 0, surfaceContainer = 0, surfaceContainerHigh = 0, surfaceContainerHighest = 0,
                surfaceContainerLow = 0, surfaceContainerLowest = 0, surfaceDim = 0, primaryFixed = 0,
                primaryFixedDim = 0, onPrimaryFixed = 0, onPrimaryFixedVariant = 0, secondaryFixed = 0,
                secondaryFixedDim = 0, onSecondaryFixed = 0, onSecondaryFixedVariant = 0, tertiaryFixed = 0,
                tertiaryFixedDim = 0, onTertiaryFixed = 0, onTertiaryFixedVariant = 0
            ))
        }
    }

    fun addDateTimeFormat(name: String, format: String){
        viewModelScope.launch {
            dateTimeFormatDao.insertDateTimeFormat(DateTimeFormat(format = format))
        }
    }

    fun addSettings(){
        viewModelScope.launch {
            selectedDao.insertSelected(Selected(1,1,1))
        }
    }

    fun changeFormatId(newId: Int){
        viewModelScope.launch {
            selectedDao.updateSelected(userSettings.value?.selected?.copy(datetimeFormatId = newId) ?: Selected(themeId = 1, datetimeFormatId = 1))
        }
    }

    fun setUserId(newId: Int){
        userId.value = newId
    }

    fun setThemeId(newId: Int){
        themeId.value = newId
    }

    fun setDateTimeFormatId(newId: Int){
        formatId.value = newId
    }
}