package eu.tvato.lempie.ui.screens.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import eu.tvato.lempie.datastore.DataStoreRepository
import eu.tvato.lempie.user.UserRepository
import eu.tvato.lempie.user.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(
    repository: UserRepository = UserRepository(),
    context: Context,
    userId: Int
): ViewModel() {
    private val dataStore = DataStoreRepository(context)

    val datetimeFormat = dataStore.getDatetimeFormat().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = ""
    )

    val contents: Flow<PagingData<UserResponse>> = repository.getUser(userId).cachedIn(viewModelScope)

    class UserViewModelFactory(
        private val context: Context,
        private val userId: Int
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserViewModel(context = context, userId = userId) as T
        }
    }
}