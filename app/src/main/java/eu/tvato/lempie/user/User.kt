package eu.tvato.lempie.user

import com.google.gson.annotations.SerializedName
import eu.tvato.lempie.comment.Comment
import eu.tvato.lempie.comment.CommentActions
import eu.tvato.lempie.community.Community
import eu.tvato.lempie.community.CommunityActions
import eu.tvato.lempie.community.CommunityTagsView
import eu.tvato.lempie.media.ImageDetails
import eu.tvato.lempie.mod.CommunityModeratorView
import eu.tvato.lempie.multicommunity.MultiCommunityView
import eu.tvato.lempie.post.Post
import eu.tvato.lempie.post.PostActions
import eu.tvato.lempie.site.Site

data class User(
    @SerializedName("id")               val id: Int,
    @SerializedName("name")             val name: String,
    @SerializedName("display_name")     val displayName: String?,
    @SerializedName("avatar")           val avatarUrl: String?,
    @SerializedName("published_at")     val published: String,
    @SerializedName("updated_at")       val updated: String?,
    @SerializedName("ap_id")            val url: String,
    @SerializedName("bio")              val bio: String?,
    @SerializedName("local")            val isLocal: Boolean,
    @SerializedName("banner")           val bannerUrl: String?,
    @SerializedName("deleted")          val isDeleted: Boolean,
    @SerializedName("matrix_user_id")   val matrixUserId: String?,
    @SerializedName("bot_account")      val isBotAccount: Boolean,
    @SerializedName("instance_id")      val instanceId: Int,
    @SerializedName("post_count")       val postCount: Int,
    @SerializedName("comment_count")    val commentCount: Int
)

data class UserView(
    @SerializedName("person")           val user: User,
    @SerializedName("is_admin")         val isAdmin: Boolean,
    @SerializedName("person_actions")   val userActions: UserActions?,
    @SerializedName("banned")           val isBanned: Boolean,
    @SerializedName("ban_expires_at")   val banExpires: String?
)

data class UserActions(
    @SerializedName("upvotes")      val upvotes: Int?,
    @SerializedName("downvotes")    val downvotes: Int?,
    @SerializedName("voted_at")     val votedAt: String?,
    @SerializedName("note")         val note: String?,
    @SerializedName("noted_at")     val notedAt: String?,
    @SerializedName("blocked_at")   val blockedAt: String?
)

sealed class ContentHolder {
    data class PostItem(
        @SerializedName("post") val post: Post,
        @SerializedName("creator") val creator: User,
        @SerializedName("community") val community: Community,
        @SerializedName("image_details") val imageDetails: ImageDetails?,
        @SerializedName("community_actions") val communityActions: CommunityActions?,
        @SerializedName("person_actions") val personActions: UserActions?,
        @SerializedName("post_actions") val postActions: PostActions?,
        @SerializedName("creator_is_admin") val creatorIsAdmin: Boolean,
        @SerializedName("tags") val tags: List<CommunityTagsView>,
        @SerializedName("can_mod") val canMod: Boolean,
        @SerializedName("creator_banned") val creatorIsBanned: Boolean,
        @SerializedName("creator_ban_expires_at") val banExpires: String?,
        @SerializedName("creator_is_moderator") val creatorIsMod: Boolean,
        @SerializedName("creator_banned_from_community") val creatorBannedFromCommunity: Boolean,
        @SerializedName("creator_community_ban_expires_at") val communityBanExpires: String?,
        @SerializedName("type_") val type: String = "post"
    ): ContentHolder()

    data class CommentContent(
        @SerializedName("comment") val comment: Comment,
        @SerializedName("creator") val creator: User,
        @SerializedName("post") val post: Post,
        @SerializedName("community") val community: Community,
        @SerializedName("community_actions") val communityActions: CommunityActions?,
        @SerializedName("comment_actions") val commentActions: CommentActions?,
        @SerializedName("person_actions") val personActions: UserActions?,
        @SerializedName("creator_is_admin") val creatorIsAdmin: Boolean,
        @SerializedName("tags") val tags: List<CommunityTagsView>,
        @SerializedName("can_mod") val canMod: Boolean,
        @SerializedName("creator_banned") val creatorBanned: Boolean,
        @SerializedName("creator_ban_expires_at") val creatorBandExpires: String?,
        @SerializedName("creator_is_moderator") val creatorIsMod: Boolean,
        @SerializedName("creator_banned_from_community") val creatorBannedFromCommunity: Boolean,
        @SerializedName("creator_community_ban_expires_at") val creatorCommunityBanExpires: String?,
        @SerializedName("type_") val type: String = "comment"
    ): ContentHolder()
}

data class UserContents(
    @SerializedName("items")        val items: List<ContentHolder>,
    @SerializedName("prev_page")    val prevPage: String?,
    @SerializedName("next_page")    val nextPage: String?
)

data class UserDetailResponse(
    @SerializedName("person_view")              val user: UserView,
    @SerializedName("site")                     val site: Site?,
    @SerializedName("moderates")                val moderates: List<CommunityModeratorView>,
    @SerializedName("multi_communities_created")val multiCommunitiesCreated: List<MultiCommunityView>
)