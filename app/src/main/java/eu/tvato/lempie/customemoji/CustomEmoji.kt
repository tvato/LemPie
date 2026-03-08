package eu.tvato.lempie.customemoji

import com.google.gson.annotations.SerializedName

data class CustomEmojis(
    @SerializedName("custom_emoji") val customEmoji: CustomEmoji,
    @SerializedName("keywords") val keywords: CustomEmojiKeywords,
)

data class CustomEmojiKeywords(
    @SerializedName("custom_emoji_id")  val customEmojiId: Int,
    @SerializedName("keyword")          val keyword: String
)

data class CustomEmoji(
    @SerializedName("id")               val id: Int,
    @SerializedName("local_site_id")    val localSiteId: Int,
    @SerializedName("shortcode")        val shortCode: String,
    @SerializedName("image_url")        val imageUrl: String,
    @SerializedName("alt_text")         val altText: String,
    @SerializedName("category")         val category: String,
    @SerializedName("published")        val published: String,
    @SerializedName("updated")          val updated: String?
)