package eu.tvato.lempie.community

import eu.tvato.lempie.api.API
import eu.tvato.lempie.api.RetrofitInstance
import eu.tvato.lempie.utils.parseIsoDate

class CommunityRepository(
    private val api: API = RetrofitInstance.api
){
    suspend fun getCommunity(communityId: Int?, communityName: String?): CommunityResponse {
        val response = api.getCommunity(name = communityName, communityId = communityId)
        return response.copy(
            communityView = response.communityView.copy(
                community = response.communityView.community.copy(
                    published = parseIsoDate(response.communityView.community.published)
                )
            )
        )
    }
}