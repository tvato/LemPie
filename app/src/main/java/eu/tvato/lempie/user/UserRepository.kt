package eu.tvato.lempie.user

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import eu.tvato.lempie.api.API
import eu.tvato.lempie.api.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val api: API = RetrofitInstance.api
){
    fun getUser(userId: Int): Flow<PagingData<UserResponse>> =
        Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                UserPagingSource(api, userId)
            }
        ).flow
}