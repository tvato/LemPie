package eu.tvato.lempie.user

import androidx.paging.PagingSource
import androidx.paging.PagingState
import eu.tvato.lempie.api.API
import eu.tvato.lempie.utils.parseIsoDate
import retrofit2.HttpException
import java.io.IOException

class UserContentPagingSource(
    private val api: API,
    private val userId: Int,
    private val type: String = "all"
): PagingSource<String, ContentHolder>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, ContentHolder> {
        return try {
            val response = api.getUserContent(userId = userId, page = params.key, type = type)
            val parsedItems = response.items.map{ item ->
                when(item) {
                    is ContentHolder.PostItem -> {
                        item.copy(
                            post = item.post.copy(
                                published = parseIsoDate(item.post.published)
                            )
                        )
                    }

                    is ContentHolder.CommentContent -> {
                        item.copy(
                            comment = item.comment.copy(
                                published = parseIsoDate(item.comment.published)
                            )
                        )
                    }
                }
            }
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

    override fun getRefreshKey(state: PagingState<String, ContentHolder>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey
                ?: state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}