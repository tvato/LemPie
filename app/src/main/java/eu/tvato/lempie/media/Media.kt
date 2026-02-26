package eu.tvato.lempie.media

import com.google.gson.annotations.SerializedName

data class ImageDetails(
    @SerializedName("link")            val link: String,
    @SerializedName("width")           val width: Int,
    @SerializedName("height")          val height: Int,
    @SerializedName("content_type")    val contentType: String
)
