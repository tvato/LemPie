package eu.tvato.lempie.community

import com.google.gson.annotations.SerializedName
import eu.tvato.lempie.misc.Tag
import eu.tvato.lempie.site.Site

data class Community(
    @SerializedName("id")                           val id: Int,
    @SerializedName("name")                         val name: String,
    @SerializedName("title")                        val title: String,
    @SerializedName("description")                  val description: String?,
    @SerializedName("removed")                      val isRemoved: Boolean,
    @SerializedName("published_at")                 val published: String,
    @SerializedName("updated_at")                   val updated: String?,
    @SerializedName("deleted")                      val isDeleted: Boolean,
    @SerializedName("nsfw")                         val isNsfw: Boolean,
    @SerializedName("ap_id")                        val url: String,
    @SerializedName("local")                        val isLocal: Boolean,
    @SerializedName("icon")                         val iconUrl: String?,
    @SerializedName("banner")                       val bannerUrl: String?,
    @SerializedName("posting_restricted_to_mods")   val postingRestrictedToMods: Boolean,
    @SerializedName("instance_id")                  val instanceId: Int,
    @SerializedName("visibility")                   val visibility: String,
    @SerializedName("summary")                      val summary: String?,
    @SerializedName("subscribers")                  val subscriberCount: Int,
    @SerializedName("posts")                        val postCount: Int,
    @SerializedName("comments")                     val commentCount: Int,
    @SerializedName("users_active_day")             val activeUsersDay: Int,
    @SerializedName("users_active_week")            val activeUsersWeek: Int,
    @SerializedName("users_active_month")           val activeUsersMonth: Int,
    @SerializedName("users_active_half_year")       val activeUsersHalfYear: Int,
    @SerializedName("subscribers_local")            val localSubscriberCount: Int,
    @SerializedName("report_count")                 val reportCount: Int,
    @SerializedName("unresolved_report_count")      val unresolvedReportCount: Int,
    @SerializedName("local_removed")                val isLocalRemoved: Boolean
)

data class CommunityActions(
    @SerializedName("followed_at")            val followedAt: String?,
    @SerializedName("blocked_at")             val blockedAt: String?,
    @SerializedName("became_moderator_at")    val becameModeratorAt: String?,
    @SerializedName("received_ban_at")        val receivedBanAt: String?,
    @SerializedName("ban_expires_at")         val banExpiresAt: String?,
    @SerializedName("follow_state")           val followState: String?,
    @SerializedName("notifications")          val notifications: String?,
)

data class CommunityTagsView(
    @SerializedName("id")           val id: Int,
    @SerializedName("ap_id")        val apId: String,
    @SerializedName("name")         val name: String,
    @SerializedName("display_name") val displayName: String?,
    @SerializedName("summary")      val summary: String?,
    @SerializedName("community_id") val communityId: Int,
    @SerializedName("published_at") val publishedAt: String,
    @SerializedName("updated_at")   val updatedAt: String?,
    @SerializedName("deleted")      val deleted: Boolean,
    @SerializedName("color")        val color: String,
)

data class CommunityResponse(
    @SerializedName("community_view")       val communityView: CommunityView,
    @SerializedName("site")                 val site: Site?,
    @SerializedName("moderators")           val moderators: List<Any>,
    @SerializedName("discussion_languages") val languages: List<Int>
)

data class CommunityView(
    @SerializedName("community")            val community: Community,
    @SerializedName("community_actions")    val communityActions: CommunityActions?,
    @SerializedName("can_mod")              val canMod: Boolean,
    @SerializedName("tags")                 val tags: List<Tag>,
)