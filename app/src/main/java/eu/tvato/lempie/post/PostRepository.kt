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
        timeRangeSeconds: String?,
        communityId: Int?,
        communityName: String?,
        multiCommunityId: Int?,
        multiCommunityName: String?,
        showHidden: Boolean?,
        showRead: Boolean?,
        showNsfw: Boolean?,
        hideMedia: Boolean?,
        markAsRead: Boolean?,
        noCommentsOnly: Boolean?,
        limit: Int?
    ): Flow<PagingData<PostItem>> =
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
                    timeRangeSeconds = timeRangeSeconds,
                    communityId = communityId,
                    communityName = communityName,
                    multiCommunityId = multiCommunityId,
                    multiCommunityName = multiCommunityName,
                    showHidden = showHidden,
                    showRead = showRead,
                    showNsfw = showNsfw,
                    hideMedia = hideMedia,
                    markAsRead = markAsRead,
                    noCommentsOnly = noCommentsOnly,
                    limit = limit,
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