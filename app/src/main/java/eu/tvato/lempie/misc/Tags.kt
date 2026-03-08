package eu.tvato.lempie.misc

import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("id")           val id: Int,
    @SerializedName("ap_id")        val url: String,
    @SerializedName("name")         val name: String,
    @SerializedName("display_name") val displayName: String?,
    @SerializedName("summary")      val summary: String?,
    @SerializedName("community_id") val communityId: Int,
    @SerializedName("published_at") val published: String,
    @SerializedName("updated_at")   val updated: String?,
    @SerializedName("deleted")      val deleted: Boolean,
    @SerializedName("color")        val color: String
)

data class TagLine(
    @SerializedName("id")               val id: Int,
    @SerializedName("local_site_id")    val localSiteId: Int,
    @SerializedName("content")          val content: String,
    @SerializedName("published")        val published: String,
    @SerializedName("updated")          val updated: String?
)
