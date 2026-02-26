package eu.tvato.lempie.post

import androidx.paging.PagingSource
import androidx.paging.PagingState
import eu.tvato.lempie.api.API
import retrofit2.HttpException
import java.io.IOException

class PostPagingSource(
    private val api: API,
    private val type: String?,
    private val sort: String?,
    private val timeRangeSeconds: String?,
    private val communityId: Int?,
    private val communityName: String?,
    private val multiCommunityId: Int?,
    private val multiCommunityName: String?,
    private val showHidden: Boolean?,
    private val showRead: Boolean?,
    private val showNsfw: Boolean?,
    private val hideMedia: Boolean?,
    private val markAsRead: Boolean?,
    private val noCommentsOnly: Boolean?,
    private val limit: Int?
): PagingSource<String, PostItem>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, PostItem> {
        return try{
            val response = api.getPosts(
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
                page = params.key
            )

            LoadResult.Page(
                data = response.items.map { it },
                prevKey = response.prevPage,
                nextKey = if(response.nextPage != params.key) response.nextPage else null
            )
        }catch(e: IOException){
            LoadResult.Error(e)
        }catch(e: HttpException){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, PostItem>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey
                ?: state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}