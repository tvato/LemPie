package eu.tvato.lempie.ui.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tvato.lempie.site.SiteRepository
import eu.tvato.lempie.site.SiteResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InstanceViewModel(
    private val repository: SiteRepository = SiteRepository()
): ViewModel() {
    private val _site = MutableStateFlow<SiteResponse?>(null)
    val site: StateFlow<SiteResponse?> = _site.asStateFlow()

    init {
        viewModelScope.launch {
            _site.value = repository.getSite()
        }
    }
}