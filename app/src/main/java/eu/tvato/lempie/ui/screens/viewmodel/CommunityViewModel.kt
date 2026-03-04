package eu.tvato.lempie.ui.screens.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import eu.tvato.lempie.community.CommunityRepository
import eu.tvato.lempie.community.CommunityResponse
import eu.tvato.lempie.datastore.DataStoreRepository
import eu.tvato.lempie.post.PostRepository
import eu.tvato.lempie.post.PostView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CommunityViewModel(
    private val communityRepository: CommunityRepository = CommunityRepository(),
    private val communityId: Int,
    postRepository: PostRepository = PostRepository(),
    context: Context
): ViewModel() {
    private val dataStore = DataStoreRepository(context)
    private val _communityName = MutableStateFlow<String?>(null)
    val communityName: StateFlow<String?> = _communityName.asStateFlow()

    private val _community = MutableStateFlow<CommunityResponse?>(null)
    val community: StateFlow<CommunityResponse?> = _community.asStateFlow()

    private val _sort = MutableStateFlow<String?>(null)
    val sort: StateFlow<String?> = _sort.asStateFlow()

    val posts: Flow<PagingData<PostView>> = postRepository.getPosts(
        type = null,
        sort = sort.value,
        communityId = communityId,
        communityName = communityName.value,
        showHidden = null,
        showRead = null,
        showNsfw = null,
        limit = null,
        savedOnly = null,
        likedOnly = null,
        dislikedOnly = null,
        pageCursor = null
    ).cachedIn(viewModelScope)

    val datetimeFormat = dataStore.getDatetimeFormat().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = ""
    )

    init{
        viewModelScope.launch {
            _community.value = communityRepository.getCommunity(communityId, communityName.value)
        }
    }

    fun changeSort(newSort: String?){
        _sort.value = newSort
    }

    class CommunityViewModelFactory(
        private val context: Context,
        private val communityId: Int
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CommunityViewModel(context = context, communityId = communityId) as T
        }
    }
}