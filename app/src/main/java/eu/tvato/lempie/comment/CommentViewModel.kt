package eu.tvato.lempie.comment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest

class CommentViewModel(
    private val repository: CommentRepository = CommentRepository()
): ViewModel() {
    private val _postId = MutableStateFlow<Int?>(null)
    val postId: StateFlow<Int?> = _postId.asStateFlow()


    @OptIn(ExperimentalCoroutinesApi::class)
    val comments: Flow<PagingData<CommentItem>> = _postId.filterNotNull().flatMapLatest { id ->
        repository.getCommentsByPostId(id)
    }.cachedIn(viewModelScope)

    fun setPostId(newPostId: Int){
        _postId.value = newPostId
    }
}