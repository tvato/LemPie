package eu.tvato.lempie.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tvato.lempie.database.datetimeformat.DateTimeFormat
import eu.tvato.lempie.database.datetimeformat.DateTimeFormatDao
import eu.tvato.lempie.database.settings.Settings
import eu.tvato.lempie.database.settings.SettingsDao
import eu.tvato.lempie.database.settings.User
import eu.tvato.lempie.database.theme.Theme
import eu.tvato.lempie.database.theme.ThemeDao
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
    //private val repository: LempieRepository
    private val settingsDao: SettingsDao,
    private val themeDao: ThemeDao,
    private val dateTimeFormatDao: DateTimeFormatDao
): ViewModel() {
    private val userId = MutableStateFlow(1)
    private val themeId = MutableStateFlow(1)
    private val formatId = MutableStateFlow(1)

    //@OptIn(ExperimentalCoroutinesApi::class)
    //val userSettings: Flow<Settings> = userId.filterNotNull().flatMapLatest { id ->
        /*val settings = repository.getUserSettings(id)
        settings.collect { (user, theme, datetimeFormat) ->
            themeId.value = theme
            formatId.value = datetimeFormat
        }
        settings*/
        //repository.getUserSettings(id)
        //settingsDao.getUserSettings(id)
    //}

    private val _settings = MutableStateFlow<Settings?>(null)
    val userSettings: StateFlow<Settings?> = _settings.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val theme: Flow<Theme> = themeId.filterNotNull().flatMapLatest { id ->
        //repository.getCurrentTheme(id)
        themeDao.getCurrentTheme(id)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val dateTimeFormat: Flow<DateTimeFormat> = formatId.filterNotNull().flatMapLatest { id ->
        //repository.getCurrentDateTimeFormat(id)
        dateTimeFormatDao.getCurrentDateTimeFormat(id)
    }

    fun loadUserSettings(){
        viewModelScope.launch {
            _settings.value = settingsDao.getUserSettings(userId.value).filterNotNull().first()
        }
    }

    fun addTheme(name: String){
        viewModelScope.launch {
            //repository.insertTheme(Theme(themeName = name))
            themeDao.insertTheme(Theme(themeName = name))
        }
    }

    fun addDateTimeFormat(name: String, format: String){
        viewModelScope.launch {
            //repository.insertDateTimeFormat(DateTimeFormat(formatName = name, datetimeFormat = format))
            dateTimeFormatDao.insertDateTimeFormat(DateTimeFormat(formatName = name, datetimeFormat = format))
        }
    }

    fun addSettings(){
        viewModelScope.launch {
            //repository.insertSettings(User(themeId = 1, datetimeFormatId = 1))
            settingsDao.insertSettings(User(themeId = 1, datetimeFormatId = 1))
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