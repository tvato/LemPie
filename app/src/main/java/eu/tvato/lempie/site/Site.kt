package eu.tvato.lempie.site

import com.google.gson.annotations.SerializedName

data class Site(
    @SerializedName("id")                   val id: Int,
    @SerializedName("name")                 val name: String,
    @SerializedName("sidebar")              val sidebar: String?,
    @SerializedName("published_at")         val published: String,
    @SerializedName("update_at")            val updated: String?,
    @SerializedName("icon")                 val iconUrl: String?,
    @SerializedName("banner")               val bannerUrl: String?,
    @SerializedName("summary")              val summary: String?,
    @SerializedName("ap_id")                val url: String,
    @SerializedName("last_refreshed_at")    val lastRefreshed: String,
    @SerializedName("inbox_url")            val inboxUrl: String,
    @SerializedName("public_key")           val publicKey: String,
    @SerializedName("instance_id")          val instanceId: Int,
    @SerializedName("content_warning")      val contentWarning: String?
)
