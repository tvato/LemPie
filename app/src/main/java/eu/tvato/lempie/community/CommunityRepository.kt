package eu.tvato.lempie.community

import eu.tvato.lempie.api.API
import eu.tvato.lempie.api.RetrofitInstance

class CommunityRepository(
    private val api: API = RetrofitInstance.api
){
    suspend fun getCommunity(communityId: Int?, communityName: String?): CommunityResponse {
        return api.getCommunity(name = communityName, communityId = communityId)
    }
}