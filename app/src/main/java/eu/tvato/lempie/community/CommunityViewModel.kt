package eu.tvato.lempie.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import eu.tvato.lempie.post.PostItem
import eu.tvato.lempie.post.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CommunityViewModel(
    private val repository: CommunityRepository = CommunityRepository(),
    private val postRepository: PostRepository = PostRepository()
): ViewModel() {
    private val _communityId = MutableStateFlow<Int?>(null)
    val communityId: StateFlow<Int?> = _communityId.asStateFlow()

    private val _communityName = MutableStateFlow<String?>(null)
    val communityName: StateFlow<String?> = _communityName.asStateFlow()

    private val _community = MutableStateFlow<CommunityResponse?>(null)
    val community: StateFlow<CommunityResponse?> = _community.asStateFlow()

    private val _sort = MutableStateFlow<String?>(null)
    val sort: StateFlow<String?> = _sort.asStateFlow()

    val posts: Flow<PagingData<PostItem>> = postRepository.getPosts(
        type = null,
        sort = sort.value,
        timeRangeSeconds = null,
        communityId = communityId.value,
        communityName = communityName.value,
        multiCommunityId = null,
        multiCommunityName = null,
        showHidden = null,
        showRead = null,
        showNsfw = null,
        hideMedia = null,
        markAsRead = null,
        noCommentsOnly = null,
        limit = null
    ).cachedIn(viewModelScope)

    fun loadCommunity(){
        viewModelScope.launch {
            _community.value = repository.getCommunity(communityId.value, communityName.value)
        }
    }

    fun setCommunityId(newId: Int){
        _communityId.value = newId
    }

    fun setCommunityName(newName: String){
        _communityName.value = newName
    }

    fun changeSort(newSort: String?){
        _sort.value = newSort
    }
}