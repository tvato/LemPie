package eu.tvato.lempie.ui.screens.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import eu.tvato.lempie.comment.CommentRepository
import eu.tvato.lempie.comment.CommentView
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

class PostViewModel(
    private val postRepository: PostRepository = PostRepository(),
    private val postId: Int,
    commentRepository: CommentRepository = CommentRepository(),
    context: Context,
): ViewModel() {
    private val dataStore = DataStoreRepository(context)
    private val _postDetails = MutableStateFlow<PostView?>(null)
    val postDetail: StateFlow<PostView?> = _postDetails.asStateFlow()

    val comments: Flow<PagingData<CommentView>> = commentRepository.getCommentsByPostId(postId).cachedIn(viewModelScope)

    val datetimeFormat = dataStore.getDatetimeFormat().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = ""
    )

    init {
        viewModelScope.launch {
            _postDetails.value = postRepository.getPost(postId = postId, commentId = null)
        }
    }

    class PostViewModelFactory(
        private val context: Context,
        private val postId: Int
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PostViewModel(context = context, postId = postId) as T
        }
    }
}