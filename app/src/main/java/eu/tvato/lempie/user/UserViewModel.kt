package eu.tvato.lempie.user


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class UserViewModel(
    private val repository: UserRepository = UserRepository()
): ViewModel() {
    private val _userId = MutableStateFlow<Int?>(null)
    val userId: StateFlow<Int?> = _userId.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val contents: Flow<PagingData<UserResponse>> = _userId.filterNotNull().flatMapLatest { id ->
        repository.getUser(id)
    }.cachedIn(viewModelScope)

    val posts = contents.map {
        it.map { (user, site, comments, posts, moderates) ->
            posts
        }
    }

    val comments = contents.map {
        it.map { (user, site, comments, posts, moderates) ->
            comments
        }
    }

    val moderates = contents.map {
        it.map { (user, site, comments, posts, moderates) ->
            moderates
        }
    }

    fun setUserId(newUserId: Int){
        _userId.value = newUserId
    }
}
