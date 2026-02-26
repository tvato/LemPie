package eu.tvato.lempie.post

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import eu.tvato.lempie.api.API
import eu.tvato.lempie.api.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class PostRepository(
    private val api: API = RetrofitInstance.api
) {
    fun getPosts(
        type: String?,
        sort: String?,
        communityId: Int?,
        communityName: String?,
        showHidden: Boolean?,
        showRead: Boolean?,
        showNsfw: Boolean?,
        limit: Int?,
        savedOnly: Boolean?,
        likedOnly: Boolean?,
        dislikedOnly: Boolean?,
        pageCursor: String?
    ): Flow<PagingData<PostView>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PostPagingSource(
                    api = api,
                    type = type,
                    sort = sort,
                    limit = limit,
                    communityId = communityId,
                    communityName = communityName,
                    savedOnly = savedOnly,
                    likedOnly = likedOnly,
                    dislikedOnly = dislikedOnly,
                    showHidden = showHidden,
                    showRead = showRead,
                    showNsfw = showNsfw,
                    pageCursor = pageCursor
                )
            }
        ).flow

    suspend fun getPost(
        postId: Int?,
        commentId: Int?
    ): PostView {
        val response = api.getPost(postId = postId, commentId = commentId)
        return response.postView
    }
}