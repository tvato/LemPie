package eu.tvato.lempie.post

import com.google.gson.annotations.SerializedName
import eu.tvato.lempie.community.Community
import eu.tvato.lempie.community.CommunityActions
import eu.tvato.lempie.community.CommunityView
import eu.tvato.lempie.media.ImageDetails
import eu.tvato.lempie.misc.Tag
import eu.tvato.lempie.user.User
import eu.tvato.lempie.user.UserActions

data class Post(
    @SerializedName("id")                       val id: Int,
    @SerializedName("name")                     val title: String,
    @SerializedName("url")                      val imageOrLink: String?,
    @SerializedName("body")                     val text: String?,
    @SerializedName("creator_id")               val userId: Int,
    @SerializedName("community_id")             val communityId: Int,
    @SerializedName("removed")                  val isRemoved: Boolean,
    @SerializedName("locked")                   val isLocked: Boolean,
    @SerializedName("published_at")             val published: String,
    @SerializedName("updated_at")               val updated: String?,
    @SerializedName("deleted")                  val isDeleted: Boolean,
    @SerializedName("nsfw")                     val isNfsw: Boolean,
    @SerializedName("embed_title")              val embedTitle: String?,
    @SerializedName("embed_description")        val embedDescription: String?,
    @SerializedName("thumbnail_url")            val thumbnailUrl: String?,
    @SerializedName("ap_id")                    val postLink: String,
    @SerializedName("local")                    val isLocal: Boolean,
    @SerializedName("embed_video_url")          val embedVideoUrl: String?,
    @SerializedName("language_id")              val languageId: Int,
    @SerializedName("featured_community")       val isFeaturedCommunity: Boolean,
    @SerializedName("featured_local")           val isFeaturedLocal: Boolean,
    @SerializedName("url_content_type")         val urlContentType: String?,
    @SerializedName("alt_text")                 val altText: String?,
    @SerializedName("scheduled_publish_time_at")val scheduledPublished: String?,
    @SerializedName("newest_comment_time_at")   val newestCommentTime: String?,
    @SerializedName("comments")                 val commentCount: Int,
    @SerializedName("score")                    val score: Int,
    @SerializedName("upvotes")                  val upvotes: Int,
    @SerializedName("downvotes")                val downvotes: Int,
    @SerializedName("report_count")             val reportCount: Int,
    @SerializedName("unresolved_report_count")  val unresolvedReportCount: Int,
    @SerializedName("federation_pending")       val isFederationPending: Boolean,
    @SerializedName("embed_video_width")        val embedVideoWidth: Int?,
    @SerializedName("embed_video_height")       val embedVideoHeight: Int?
)

data class PostItem(
    @SerializedName("post")                          val post: Post,
    @SerializedName("creator")                       val creator: User,
    @SerializedName("community")                     val community: Community,
    @SerializedName("creator_is_admin")              val creatorIsAdmin: Boolean,
    @SerializedName("tags")                          val tags: List<Tag>,
    @SerializedName("can_mod")                       val canMod: Boolean,
    @SerializedName("creator_banned")                val creatorIsBanned: Boolean,
    @SerializedName("creator_is_moderator")          val creatorIsMod: Boolean,
    @SerializedName("creator_banned_from_community") val creatorBannedFromCommunity: Boolean
)

data class PostActions(
    @SerializedName("read_at")                 val readAt: String?,
    @SerializedName("read_comments_at")        val readCommentsAt: String?,
    @SerializedName("saved_at")                val savedAt: String?,
    @SerializedName("voted_at")                val votedAt: String?,
    @SerializedName("hidden_at")               val hiddenAt: String?,
    @SerializedName("read_comments_amount")    val readCommentsAmount: Int?,
    @SerializedName("vote_is_upvote")          val voteIsUpvote: Boolean?,
    @SerializedName("notifications")           val notifications: String?,
)

data class PostView(
    @SerializedName("post")                                val post: Post,
    @SerializedName("creator")                             val creator: User,
    @SerializedName("community")                           val community: Community,
    @SerializedName("image_details")                       val imageDetails: ImageDetails?,
    @SerializedName("community_actions")                   val communityActions: CommunityActions?,
    @SerializedName("person_actions")                      val personActions: UserActions?,
    @SerializedName("post_actions")                        val postActions: PostActions?,
    @SerializedName("creator_is_admin")                    val creatorIsAdmin: Boolean,
    @SerializedName("tags")                                val tags: List<Tag>,
    @SerializedName("can_mod")                             val canMod: Boolean,
    @SerializedName("creator_banned")                      val creatorBanned: Boolean,
    @SerializedName("creator_ban_expires_at")              val creatorBanExpiresAt: String?,
    @SerializedName("creator_is_moderator")                val creatorIsModerator: Boolean,
    @SerializedName("creator_banned_from_community")       val creatorBannedFromCommunity: Boolean,
    @SerializedName("creator_community_ban_expires_at")    val creatorCommunityBanExpiresAt: String?,
)

data class PostResponse(
    @SerializedName("post_view")         val postView: PostView,
    @SerializedName("community_view")    val communityView: CommunityView,
    @SerializedName("cross_posts")       val crossPosts: List<PostView>,
)

data class PostsResponse(
    @SerializedName("items")     val items: List<PostItem>,
    @SerializedName("next_page") val nextPage: String?,
    @SerializedName("prev_page") val prevPage: String?
)
