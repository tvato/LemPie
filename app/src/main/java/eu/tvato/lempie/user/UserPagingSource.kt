package eu.tvato.lempie.user

import androidx.paging.PagingSource
import androidx.paging.PagingState
import eu.tvato.lempie.api.API
import retrofit2.HttpException
import java.io.IOException

class UserPagingSource(
    private val api: API,
    private val userId: Int
): PagingSource<Int, UserResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserResponse> {
        return try {
            val response = api.getUserDetail(userId = userId, page = params.key)
            LoadResult.Page(
                data = listOf(response),
                prevKey = params.key?.minus(1),
                nextKey = params.key?.plus(1)
            )
        }catch(e: IOException){
            LoadResult.Error(e)
        }catch(e: HttpException){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
                ?: state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
        }
    }
}
