package eu.tvato.lempie.site

import eu.tvato.lempie.api.API
import eu.tvato.lempie.api.RetrofitInstance

class SiteRepository(
    private val api: API = RetrofitInstance.api
) {
    suspend fun getSite(): SiteResponse {
        return api.getSite()
    }
}