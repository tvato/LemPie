package eu.tvato.lempie.site

import com.google.gson.annotations.SerializedName

data class Site(
    @SerializedName("id")                   val id: Int,
    @SerializedName("name")                 val name: String,
    @SerializedName("sidebar")              val sidebar: String?,
    @SerializedName("published")            val published: String,
    @SerializedName("updated")              val updated: String?,
    @SerializedName("icon")                 val iconUrl: String?,
    @SerializedName("banner")               val bannerUrl: String?,
    @SerializedName("description")          val description: String?,
    @SerializedName("actor_id")             val actorId: String,
    @SerializedName("last_refreshed_at")    val lastRefreshed: String,
    @SerializedName("inbox_url")            val inboxUrl: String,
    @SerializedName("public_key")           val publicKey: String,
    @SerializedName("instance_id")          val instanceId: Int,
    @SerializedName("content_warning")      val contentWarning: String?
)
