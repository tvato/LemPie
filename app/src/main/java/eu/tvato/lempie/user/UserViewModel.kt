package eu.tvato.lempie.user


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository = UserRepository()
): ViewModel() {
    private val _userId = MutableStateFlow<Int?>(null)
    val userId: StateFlow<Int?> = _userId.asStateFlow()
    private val _type = MutableStateFlow("all")
    val type: StateFlow<String> = _type.asStateFlow()

    private val _details = MutableStateFlow<UserDetailResponse?>(null)
    val details: StateFlow<UserDetailResponse?> = _details

    // Old code, keeping it just in case...
    /*@OptIn(ExperimentalCoroutinesApi::class)
    val contents: Flow<PagingData<ContentHolder>> = _userId.filterNotNull().flatMapLatest { id ->
        repository.getUserContents(id, type.value)
    }.cachedIn(viewModelScope)*/

    @OptIn(ExperimentalCoroutinesApi::class)
    val contents: Flow<PagingData<ContentHolder>> = combine(
        _userId.filterNotNull(), _type.filterNotNull()
    ){ id, contentType ->
        repository.getUserContents(id, contentType)
    }.flatMapLatest { it }.cachedIn(viewModelScope)

    fun loadUserDetails() {
        viewModelScope.launch {
            _details.value = repository.getUserDetails(userId.value)
        }
    }

    fun setUserId(newUserId: Int){
        _userId.value = newUserId
    }

    fun setContentType(newType: String){
        _type.value = newType
    }
}
