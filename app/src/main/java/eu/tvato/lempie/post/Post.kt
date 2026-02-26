package eu.tvato.lempie.post

import com.google.gson.annotations.SerializedName
import eu.tvato.lempie.community.Community
import eu.tvato.lempie.community.CommunityView
import eu.tvato.lempie.media.ImageDetails
import eu.tvato.lempie.mod.Moderator
import eu.tvato.lempie.user.User

data class Post(
    @SerializedName("id")                       val id: Int,
    @SerializedName("name")                     val title: String,
    @SerializedName("url")                      val imageOrLink: String?,
    @SerializedName("body")                     val text: String?,
    @SerializedName("creator_id")               val userId: Int,
    @SerializedName("community_id")             val communityId: Int,
    @SerializedName("removed")                  val isRemoved: Boolean,
    @SerializedName("locked")                   val isLocked: Boolean,
    @SerializedName("published")                val published: String,
    @SerializedName("updated")                  val updated: String?,
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
    @SerializedName("alt_text")                 val altText: String?
)

data class PostCounts(
    @SerializedName("post_id") val postId: Int,
    @SerializedName("comments") val commentCount: Int,
    @SerializedName("score") val score: Int,
    @SerializedName("upvotes") val upvotes: Int,
    @SerializedName("downvotes") val downvotes: Int,
    @SerializedName("published") val published: String,
    @SerializedName("newest_comment_time") val newestCommentTime: String?
)

data class PostView(
    @SerializedName("post")                                val post: Post,
    @SerializedName("creator")                             val creator: User,
    @SerializedName("community")                           val community: Community,
    @SerializedName("image_details")                       val imageDetails: ImageDetails?,
    @SerializedName("creator_banned_from_community")       val creatorBannedFromCommunity: Boolean,
    @SerializedName("banned_from_community")               val bannedFromCommunity: Boolean,
    @SerializedName("creator_is_moderator")                val creatorIsMod: Boolean,
    @SerializedName("creator_is_admin")                    val creatorIsAdmin: Boolean,
    @SerializedName("counts")                              val counts: PostCounts,
    @SerializedName("subscribed")                          val subscribed: String,
    @SerializedName("saved")                               val isSaved: Boolean,
    @SerializedName("read")                                val isRead: Boolean,
    @SerializedName("hidden")                              val isHidden: Boolean,
    @SerializedName("creator_blocked")                     val creatorBlocked: Boolean,
    @SerializedName("my_vote")                             val myVote: Int?,
    @SerializedName("unread_comments")                     val unreadComments: Int,
)

data class PostResponse(
    @SerializedName("post_view")         val postView: PostView,
    @SerializedName("community_view")    val communityView: CommunityView,
    @SerializedName("moderators")        val moderators: List<Moderator>,
    @SerializedName("cross_posts")       val crossPosts: List<PostView>,
)

data class PostsResponse(
    @SerializedName("posts")     val posts: List<PostView>,
    @SerializedName("next_page") val nextPage: String?,
    @SerializedName("prev_page") val prevPage: String?
)
