package eu.tvato.lempie.multicommunity

import com.google.gson.annotations.SerializedName
import eu.tvato.lempie.user.User

data class MultiCommunity(
    @SerializedName("id")                   val id: Int,
    @SerializedName("creator_id")           val creatorId: Int,
    @SerializedName("instance_id")          val instanceId: Int,
    @SerializedName("name")                 val name: String,
    @SerializedName("title")                val title: String?,
    @SerializedName("summary")              val summary: String?,
    @SerializedName("local")                val isLocal: Boolean,
    @SerializedName("deleted")              val isDeleted: Boolean,
    @SerializedName("ap_id")                val url: String,
    @SerializedName("published_at")         val published: String,
    @SerializedName("updated_at")           val updated: String?,
    @SerializedName("subscribers")          val subscribers: Int,
    @SerializedName("subscribers_local")    val localSubscribers: Int,
    @SerializedName("communities")          val communities: Int
)

data class MultiCommunityView(
    @SerializedName("multi")            val multiCommunity: MultiCommunity,
    @SerializedName("follow_state")     val followState: String?,
    @SerializedName("owner")            val owner: User,
)
