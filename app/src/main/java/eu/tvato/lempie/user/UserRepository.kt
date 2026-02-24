package eu.tvato.lempie.user

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import eu.tvato.lempie.api.API
import eu.tvato.lempie.api.RetrofitInstance
import eu.tvato.lempie.utils.parseIsoDate
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val api: API = RetrofitInstance.api
){
    fun getUserContents(userId: Int, type: String): Flow<PagingData<ContentHolder>> =
        Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                UserContentPagingSource(api, userId, type)
            }
        ).flow

    suspend fun getUserDetails(userId: Int?): UserDetailResponse {
        val response = api.getUserDetail(userId = userId)
        return response.copy(
            user = response.user.copy(
                user = response.user.user.copy(
                    published = parseIsoDate(response.user.user.published)
                )
            )
        )
    }
}