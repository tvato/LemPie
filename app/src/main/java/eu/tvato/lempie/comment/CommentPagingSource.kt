package eu.tvato.lempie.comment

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import eu.tvato.lempie.api.API
import eu.tvato.lempie.utils.CommentUtils
import retrofit2.HttpException
import java.io.IOException

class CommentPagingSource(
    private val api: API,
    private val postId: Int
): PagingSource<Int, CommentView>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CommentView> {
        return try{
            val page = params.key ?: 1
            val response = api.getCommentsByPostId(postId = postId, page = page, sort = "New", maxDepth = 8)
            val sortedComments = CommentUtils.addAndSort(response.comments)
            LoadResult.Page(
                data = sortedComments,
                prevKey = null,
                nextKey = if(sortedComments.isEmpty()) null else page + 1
            )
        }catch(e: IOException){
            LoadResult.Error(e)
        }catch(e: HttpException){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CommentView>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
                ?: state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
        }
    }
}