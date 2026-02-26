package eu.tvato.lempie.community

import com.google.gson.annotations.SerializedName
import eu.tvato.lempie.mod.Moderator
import eu.tvato.lempie.site.Site

data class Community(
    @SerializedName("id")                           val id: Int,
    @SerializedName("name")                         val name: String,
    @SerializedName("title")                        val title: String,
    @SerializedName("description")                  val description: String?,
    @SerializedName("removed")                      val isRemoved: Boolean,
    @SerializedName("published")                    val published: String,
    @SerializedName("updated")                      val updated: String?,
    @SerializedName("deleted")                      val isDeleted: Boolean,
    @SerializedName("nsfw")                         val isNsfw: Boolean,
    @SerializedName("actor_id")                     val actorId: String,
    @SerializedName("local")                        val isLocal: Boolean,
    @SerializedName("icon")                         val iconUrl: String?,
    @SerializedName("banner")                       val bannerUrl: String?,
    @SerializedName("hidden")                       val isHidden: Boolean,
    @SerializedName("posting_restricted_to_mods")   val postingRestrictedToMods: Boolean,
    @SerializedName("instance_id")                  val instanceId: Int,
    @SerializedName("visibility")                   val visibility: String
)

data class CommunityResponse(
    @SerializedName("community_view")       val communityView: CommunityView,
    @SerializedName("site")                 val site: Site?,
    @SerializedName("moderators")           val moderators: List<Moderator>,
    @SerializedName("discussion_languages") val languages: List<Int>
)

data class CommunityView(
    @SerializedName("community")             val community: Community,
    @SerializedName("subscribed")            val subscribed: String,
    @SerializedName("blocked")               val isBlocked: Boolean,
    @SerializedName("counts")                val counts: CommunityCounts,
    @SerializedName("banned_from_community") val bannedFromCommunity: Boolean,
)

data class CommunityCounts(
    @SerializedName("community_id")             val communityId: Int,
    @SerializedName("subscribers")              val subscriberCount: Int,
    @SerializedName("posts")                    val postCount: Int,
    @SerializedName("comments")                 val commentCount: Int,
    @SerializedName("published")                val published: String,
    @SerializedName("users_active_day")         val activeUsersDay: Int,
    @SerializedName("users_active_week")        val activeUsersWeek: Int,
    @SerializedName("users_active_month")       val activeUsersMonth: Int,
    @SerializedName("users_active_half_year")   val activeUsers6Month: Int,
    @SerializedName("subscribers_local")        val localSubscribersCount: Int
)