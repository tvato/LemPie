package eu.tvato.lempie.post

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel(
    private val repository: PostRepository = PostRepository()
): ViewModel() {
    private val _type = MutableStateFlow("All")
    val type: StateFlow<String> = _type.asStateFlow()

    private val _sort = MutableStateFlow<String?>(null)
    val sort: StateFlow<String?> = _sort.asStateFlow()

    private val _communityId = MutableStateFlow<Int?>(null)
    val communityId: StateFlow<Int?> = _communityId.asStateFlow()

    private val _communityName = MutableStateFlow<String?>(null)
    val communityName: StateFlow<String?> = _communityName.asStateFlow()

    private val _showHidden = MutableStateFlow<Boolean?>(null)
    val showHidden: StateFlow<Boolean?> = _showHidden.asStateFlow()

    private val _showRead = MutableStateFlow<Boolean?>(null)
    val showRead: StateFlow<Boolean?> = _showRead.asStateFlow()

    private val _showNsfw = MutableStateFlow<Boolean?>(null)
    val showNsfw: StateFlow<Boolean?> = _showNsfw.asStateFlow()

    private val _limit = MutableStateFlow<Int?>(null)
    val limit: StateFlow<Int?> = _limit.asStateFlow()

    private val _savedOnly = MutableStateFlow<Boolean?>(null)
    val savedOnly: StateFlow<Boolean?> = _savedOnly.asStateFlow()

    private val _likedOnly = MutableStateFlow<Boolean?>(null)
    val likedOnly: StateFlow<Boolean?> = _likedOnly.asStateFlow()

    private val _dislikedOnly = MutableStateFlow<Boolean?>(null)
    val dislikedOnly: StateFlow<Boolean?> = _dislikedOnly.asStateFlow()

    private val _pageCursor = MutableStateFlow<String?>(null)
    val pageCursor: StateFlow<String?> = _pageCursor.asStateFlow()

    private val _postDetails = MutableStateFlow<PostView?>(null)
    val postDetail: StateFlow<PostView?> = _postDetails.asStateFlow()

    private val _postId = MutableStateFlow<Int?>(null)
    val postId: StateFlow<Int?> = _postId.asStateFlow()
    val posts: Flow<PagingData<PostView>> = repository.getPosts(
        type = type.value,
        sort = sort.value,
        communityId = communityId.value,
        communityName = communityName.value,
        showHidden = showHidden.value,
        showRead = showRead.value,
        showNsfw = showNsfw.value,
        limit = limit.value,
        savedOnly = savedOnly.value,
        likedOnly = likedOnly.value,
        dislikedOnly = dislikedOnly.value,
        pageCursor = pageCursor.value
    ).cachedIn(viewModelScope)
    fun loadPostDetails(){
        viewModelScope.launch {
            _postDetails.value = repository.getPost(postId = postId.value, commentId = null)
        }
    }

    fun changeType(newType: String){ _type.value = newType }
    fun changeSort(newSort: String){ _sort.value = newSort }
    fun changeCommunityId(newCommunityId: Int?){ _communityId.value = newCommunityId }
    fun changeCommunityName(newCommunityName: String?){ _communityName.value = newCommunityName }
    fun changeShowHidden(newShowHidden: Boolean?){ _showHidden.value = newShowHidden }
    fun changeShowRead(newShowRead: Boolean?){ _showRead.value = newShowRead }
    fun changeShowNsfw(newShowNsfw: Boolean?){ _showNsfw.value = newShowNsfw }
    fun changeLimit(newLimit: Int?){ _limit.value = newLimit }
    fun changeSavedOnly(newValue: Boolean?){ _savedOnly.value = newValue }
    fun changeLikedOnly(newValue: Boolean?){ _likedOnly.value = newValue }
    fun changeDislikedOnly(newValue: Boolean?){ _dislikedOnly.value = newValue }
    fun changePageCursor(newCursor: String?){ _pageCursor.value = newCursor }
    fun setPostId(newPostId: Int){ _postId.value = newPostId }
}