package eu.tvato.lempie.comment

import com.google.gson.annotations.SerializedName
import eu.tvato.lempie.community.Community
import eu.tvato.lempie.misc.Tag
import eu.tvato.lempie.post.Post
import eu.tvato.lempie.user.User

data class Comment(
    @SerializedName("id")                       val id: Int,
    @SerializedName("creator_id")               val userId: Int,
    @SerializedName("post_id")                  val postId: Int,
    @SerializedName("content")                  val content: String,
    @SerializedName("removed")                  val isRemoved: Boolean,
    @SerializedName("published_at")             val published: String,
    @SerializedName("updated_at")               val updated: String?,
    @SerializedName("deleted")                  val isDeleted: Boolean,
    @SerializedName("ap_id")                    val commentLink: String,
    @SerializedName("local")                    val isLocal: Boolean,
    @SerializedName("path")                     val path: String,
    @SerializedName("distinguished")            val isDistinguished: Boolean,
    @SerializedName("language_id")              val languageId: Int,
    @SerializedName("score")                    val score: Int,
    @SerializedName("upvotes")                  val upvotes: Int,
    @SerializedName("downvotes")                val downvotes: Int,
    @SerializedName("child_count")              val childCount: Int,
    @SerializedName("report_count")             val reportCount: Int,
    @SerializedName("unresolved_report_count")  val unresolvedReportCount: Int,
    @SerializedName("federation_pending")       val federationPending: Boolean,
    @SerializedName("locked")                   val isLocked: Boolean
)

data class CommentItem(
    @SerializedName("comment")                          val comment: Comment,
    @SerializedName("creator")                          val creator: User,
    @SerializedName("post")                             val post: Post,
    @SerializedName("community")                        val community: Community,
    @SerializedName("community_actions")                val communityActions: List<String?>?,
    @SerializedName("comment_actions")                  val commentActions: List<Any?>?,
    @SerializedName("person_actions")                   val personActions: List<Any?>?,
    @SerializedName("creator_is_admin")                 val creatorIsAdmin: Boolean,
    @SerializedName("post_tags")                        val postTags: List<Tag>?,
    @SerializedName("can_mod")                          val canMod: Boolean,
    @SerializedName("creator_banned")                   val creatorBanned: Boolean,
    @SerializedName("creator_ban_expires_at")           val creatorBandExpires: String?,
    @SerializedName("creator_is_moderator")             val creatorIsMod: Boolean,
    @SerializedName("creator_banned_from_community")    val creatorBannedFromCommunity: Boolean,
    @SerializedName("creator_community_ban_expires_at") val creatorCommunityBanExpires: String?
)

data class CommentActions(
    @SerializedName("voted_at")          val votedAt: String?,
    @SerializedName("saved_at")          val savedAt: String?,
    @SerializedName("vote_is_upvote")    val voteIsUpvote: Boolean?,
)

data class CommentResponse(
    @SerializedName("items")        val items: List<CommentItem>,
    @SerializedName("next_page")    val nextPage: String?,
    @SerializedName("prev_page")    val prevPage: String?
)