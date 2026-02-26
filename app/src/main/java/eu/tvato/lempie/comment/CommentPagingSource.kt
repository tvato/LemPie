package eu.tvato.lempie.comment

import androidx.paging.PagingSource
import androidx.paging.PagingState
import eu.tvato.lempie.api.API
import retrofit2.HttpException
import java.io.IOException

class CommentPagingSource(
    private val api: API,
    private val postId: Int
): PagingSource<String, CommentItem>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, CommentItem> {
        return try{
            val response = api.getCommentsByPostId(postId = postId, page = params.key)
            val parsedItems = response.items.sortedBy{ it.comment.path.split(".").size }.sortedBy { it.comment.path.split(".")[1] }
            LoadResult.Page(
                data = parsedItems,
                prevKey = response.prevPage,
                nextKey = if(response.nextPage != params.key) response.nextPage else null
            )
        }catch(e: IOException){
            LoadResult.Error(e)
        }catch(e: HttpException){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, CommentItem>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey
                ?: state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}