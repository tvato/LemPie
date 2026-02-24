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
