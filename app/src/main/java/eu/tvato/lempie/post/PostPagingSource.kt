package eu.tvato.lempie.post

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import eu.tvato.lempie.api.API
import retrofit2.HttpException
import java.io.IOException

class PostPagingSource(
    private val api: API,
    private val type: String?,
    private val sort: String?,
    private val communityId: Int?,
    private val communityName: String?,
    private val showHidden: Boolean?,
    private val showRead: Boolean?,
    private val showNsfw: Boolean?,
    private val limit: Int?,
    private val savedOnly: Boolean?,
    private val likedOnly: Boolean?,
    private val dislikedOnly: Boolean?,
    private val pageCursor: String?
): PagingSource<String, PostView>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, PostView> {
        return try{
            val response = api.getPosts(
                type = type,
                sort = sort,
                page = null,
                limit = limit,
                communityId = communityId,
                communityName = communityName,
                savedOnly = savedOnly,
                likedOnly = likedOnly,
                dislikedOnly = dislikedOnly,
                showHidden = showHidden,
                showRead = showRead,
                showNsfw = showNsfw,
                pageCursor = params.key
            )

            LoadResult.Page(
                data = response.posts.map { it },
                prevKey = params.key,
                nextKey = if(response.nextPage != params.key) response.nextPage else null
            )
        }catch(e: IOException){
            LoadResult.Error(e)
        }catch(e: HttpException){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, PostView>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey
                ?: state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}