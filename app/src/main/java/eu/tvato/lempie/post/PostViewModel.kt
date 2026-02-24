package eu.tvato.lempie.post

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
    private val _type = MutableStateFlow("all")
    val type: StateFlow<String> = _type.asStateFlow()

    private val _sort = MutableStateFlow<String?>(null)
    val sort: StateFlow<String?> = _sort.asStateFlow()

    private val _timeRangeSeconds = MutableStateFlow<String?>(null)
    val timeRangeSeconds: StateFlow<String?> = _timeRangeSeconds.asStateFlow()

    private val _communityId = MutableStateFlow<Int?>(null)
    val communityId: StateFlow<Int?> = _communityId.asStateFlow()

    private val _communityName = MutableStateFlow<String?>(null)
    val communityName: StateFlow<String?> = _communityName.asStateFlow()

    private val _multiCommunityId = MutableStateFlow<Int?>(null)
    val multiCommunityId: StateFlow<Int?> = _multiCommunityId.asStateFlow()

    private val _multiCommunityName = MutableStateFlow<String?>(null)
    val multiCommunityName: StateFlow<String?> = _multiCommunityName.asStateFlow()

    private val _showHidden = MutableStateFlow<Boolean?>(null)
    val showHidden: StateFlow<Boolean?> = _showHidden.asStateFlow()

    private val _showRead = MutableStateFlow<Boolean?>(null)
    val showRead: StateFlow<Boolean?> = _showRead.asStateFlow()

    private val _showNsfw = MutableStateFlow<Boolean?>(null)
    val showNsfw: StateFlow<Boolean?> = _showNsfw.asStateFlow()

    private val _hideMedia = MutableStateFlow<Boolean?>(null)
    val hideMedia: StateFlow<Boolean?> = _hideMedia.asStateFlow()

    private val _markAsRead = MutableStateFlow<Boolean?>(null)
    val markAsRead: StateFlow<Boolean?> = _markAsRead.asStateFlow()

    private val _noCommentsOnly = MutableStateFlow<Boolean?>(null)
    val noCommentsOnly: StateFlow<Boolean?> = _noCommentsOnly.asStateFlow()

    private val _limit = MutableStateFlow<Int?>(null)
    val limit: StateFlow<Int?> = _limit.asStateFlow()

    private val _postDetails = MutableStateFlow<PostView?>(null)
    val postDetail: StateFlow<PostView?> = _postDetails.asStateFlow()

    private val _postId = MutableStateFlow<Int?>(null)
    val postId: StateFlow<Int?> = _postId.asStateFlow()
    val posts: Flow<PagingData<PostItem>> = repository.getPosts(
        type = type.value,
        sort = sort.value,
        timeRangeSeconds = timeRangeSeconds.value,
        communityId = communityId.value,
        communityName = communityName.value,
        multiCommunityId = multiCommunityId.value,
        multiCommunityName = multiCommunityName.value,
        showHidden = showHidden.value,
        showRead = showRead.value,
        showNsfw = showNsfw.value,
        hideMedia = hideMedia.value,
        markAsRead = markAsRead.value,
        noCommentsOnly = noCommentsOnly.value,
        limit = limit.value
    ).cachedIn(viewModelScope)

    fun loadPostDetails(){
        viewModelScope.launch {
            _postDetails.value = repository.getPost(postId = postId.value, commentId = null)
        }
    }

    fun changeType(newType: String){ _type.value = newType }
    fun changeSort(newSort: String){ _sort.value = newSort }
    fun changeTimeRangeSeconds(newTimeRangeSeconds: String?){ _timeRangeSeconds.value = newTimeRangeSeconds }
    fun changeCommunityId(newCommunityId: Int?){ _communityId.value = newCommunityId }
    fun changeCommunityName(newCommunityName: String?){ _communityName.value = newCommunityName }
    fun changeMultiCommunityId(newMultiCommunityId: Int?){ _multiCommunityId.value = newMultiCommunityId }
    fun changeMultiCommunityName(newMultiCommunityName: String?){ _multiCommunityName.value = newMultiCommunityName }
    fun changeShowHidden(newShowHidden: Boolean?){ _showHidden.value = newShowHidden }
    fun changeShowRead(newShowRead: Boolean?){ _showRead.value = newShowRead }
    fun changeShowNsfw(newShowNsfw: Boolean?){ _showNsfw.value = newShowNsfw }
    fun changeHideMedia(newHideMedia: Boolean?){ _hideMedia.value = newHideMedia }
    fun changeMarkAsRead(newMarkAsRead: Boolean?){ _markAsRead.value = newMarkAsRead }
    fun changeNoCommentsOnly(newNoCommentsOnly: Boolean?){ _noCommentsOnly.value = newNoCommentsOnly }
    fun changeLimit(newLimit: Int?){ _limit.value = newLimit }
    fun setPostId(newPostId: Int){ _postId.value = newPostId }
}