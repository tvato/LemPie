package eu.tvato.lempie.comment

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import eu.tvato.lempie.api.API
import eu.tvato.lempie.api.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class CommentRepository(
    private val api: API = RetrofitInstance.api
) {
    fun getCommentsByPostId(postId: Int): Flow<PagingData<CommentItem>> =
        Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CommentPagingSource(api, postId)
            }
        ).flow
}