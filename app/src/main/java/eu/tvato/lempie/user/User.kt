package eu.tvato.lempie.user

import com.google.gson.annotations.SerializedName
import eu.tvato.lempie.comment.CommentView
import eu.tvato.lempie.community.Community
import eu.tvato.lempie.mod.Moderator
import eu.tvato.lempie.post.PostView
import eu.tvato.lempie.site.Site

data class User(
    @SerializedName("id")               val id: Int,
    @SerializedName("name")             val name: String,
    @SerializedName("display_name")     val displayName: String?,
    @SerializedName("avatar")           val avatarUrl: String?,
    @SerializedName("published")        val published: String,
    @SerializedName("updated")          val updated: String?,
    @SerializedName("actor_id")         val actorId: String,
    @SerializedName("bio")              val bio: String?,
    @SerializedName("local")            val isLocal: Boolean,
    @SerializedName("banner")           val bannerUrl: String?,
    @SerializedName("deleted")          val isDeleted: Boolean,
    @SerializedName("matrix_user_id")   val matrixUserId: String?,
    @SerializedName("bot_account")      val isBotAccount: Boolean,
    @SerializedName("ban_expires")      val banExpires: String?,
    @SerializedName("instance_id")      val instanceId: Int
)

data class LocalUser(
    @SerializedName("id")                           val id: Int,
    @SerializedName("person_id")                    val userId: Int,
    @SerializedName("email")                        val email: String?,
    @SerializedName("show_nsfw")                    val showNsfw: Boolean,
    @SerializedName("theme")                        val theme: String,
    @SerializedName("default_sort_type")            val defaultSortType: String,
    @SerializedName("default_listing_type")         val defaultListingType: String,
    @SerializedName("interface_language")           val interfaceLanguage: String,
    @SerializedName("show_avatars")                 val showAvatars: Boolean,
    @SerializedName("send_notifications_to_email")  val sendNotificationToEmail: Boolean,
    @SerializedName("show_scores")                  val showScores: Boolean,
    @SerializedName("show_bot_accounts")            val showBotAccounts: Boolean,
    @SerializedName("show_read_posts")              val showReadPosts: Boolean,
    @SerializedName("email_verified")               val emailVerified: Boolean,
    @SerializedName("accepted_application")         val acceptedApplication: Boolean,
    @SerializedName("open_links_in_new_tab")        val openLinksInNewTab: Boolean,
    @SerializedName("blur_nsfw")                    val blurNsfw: Boolean,
    @SerializedName("auto_expand")                  val autoExpand: Boolean,
    @SerializedName("infinite_scroll_enabled")      val infiniteScroll: Boolean,
    @SerializedName("admin")                        val isAdmin: Boolean,
    @SerializedName("post_listing_mode")            val postListingMode: String,
    @SerializedName("totp_2fa_enabled")             val enabled2fa: Boolean,
    @SerializedName("enable_keyboard_navigation")   val keyboardNavigation: Boolean,
    @SerializedName("enable_animated_images")       val animatedImages: Boolean,
    @SerializedName("collapse_bot_comments")        val collapseBotComments: Boolean,
    @SerializedName("last_donation_notification")  val lastDonationNotification: String,
)

data class MyUserInfo(
    @SerializedName("local_user_view")  val localUser: LocalUserView,
    @SerializedName("follows")          val follows: List<Follows>,
    @SerializedName("moderates")        val moderates: List<Moderator>,
    @SerializedName("community_blocks") val communityBlocks: List<CommunityBlocks>,
    @SerializedName("instance_blocks")  val instanceBlocks: List<InstanceBlocks>,
    @SerializedName("person_blocks")    val personBlocks: List<UserBlocks>,
    @SerializedName("discussion_languages") val languages: List<Int>
)

data class Follows(
    @SerializedName("community")    val community: Community,
    @SerializedName("follower")     val follower: User
)

data class CommunityBlocks(
    @SerializedName("person")       val user: User,
    @SerializedName("community")    val community: Community
)

data class InstanceBlocks(
    @SerializedName("person")   val user: User,
    @SerializedName("instance") val instance: Instance,
    @SerializedName("site")     val site: Site
)

data class UserBlocks(
    @SerializedName("person")   val user: User,
    @SerializedName("target")   val target: User
)

data class Instance(        // TODO() Move this somewhere?
    @SerializedName("id")           val id: Int,
    @SerializedName("domain")       val domain: String,
    @SerializedName("published")    val published: String,
    @SerializedName("updated")      val updated: String?,
    @SerializedName("software")     val software: String?,
    @SerializedName("version")      val version: String?
)

data class LocalUserView(
    @SerializedName("local_user")                   val localUser: LocalUser,
    @SerializedName("local_user_vote_display_mode") val voteDisplayMode: LocalUserVoteDisplayMode,
    @SerializedName("person")                       val user: User,
    @SerializedName("counts")                       val counts: UserCounts
)

data class LocalUserVoteDisplayMode(
    @SerializedName("local_user_id")        val localUserId: Int,
    @SerializedName("score")                val score: Boolean,
    @SerializedName("upvotes")              val upvotes: Boolean,
    @SerializedName("downvotes")            val downvotes: Boolean,
    @SerializedName("upvote_percentage")    val upvotePercentage: Boolean,
)

data class UserCounts(
    @SerializedName("person_id") val userId: Int,
    @SerializedName("comment_count") val commentCount: Int,
    @SerializedName("post_count") val postCount: Int,
)

data class UserView(
    @SerializedName("person")           val user: User,
    @SerializedName("counts")           val counts: UserCounts,
    @SerializedName("is_admin")         val isAdmin: Boolean,
)

data class UserResponse(
    @SerializedName("person_view")              val user: UserView,
    @SerializedName("site")                     val site: Site?,
    @SerializedName("comments")                 val comments: List<CommentView>,
    @SerializedName("posts")                    val posts: List<PostView>,
    @SerializedName("moderates")                val moderates: List<Moderator>
)