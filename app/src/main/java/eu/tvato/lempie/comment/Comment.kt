package eu.tvato.lempie.comment

import com.google.gson.annotations.SerializedName
import eu.tvato.lempie.community.Community
import eu.tvato.lempie.post.Post
import eu.tvato.lempie.user.User

data class Comment(
    @SerializedName("id")               val id: Int,
    @SerializedName("creator_id")       val userId: Int,
    @SerializedName("post_id")          val postId: Int,
    @SerializedName("content")          val content: String,
    @SerializedName("removed")          val isRemoved: Boolean,
    @SerializedName("published")        val published: String,
    @SerializedName("updated")          val updated: String?,
    @SerializedName("deleted")          val isDeleted: Boolean,
    @SerializedName("ap_id")            val commentLink: String,
    @SerializedName("local")            val isLocal: Boolean,
    @SerializedName("path")             val path: String,
    @SerializedName("distinguished")    val isDistinguished: Boolean,
    @SerializedName("language_id")      val languageId: Int
)

data class CommentCounts(
    @SerializedName("comment_id") val postId: Int,
    @SerializedName("score") val score: Int,
    @SerializedName("upvotes") val upvotes: Int,
    @SerializedName("downvotes") val downvotes: Int,
    @SerializedName("published") val published: String,
    @SerializedName("child_count") val childCounts: Int?
)

data class CommentView(
    @SerializedName("comment")                          val comment: Comment,
    @SerializedName("creator")                          val creator: User,
    @SerializedName("post")                             val post: Post,
    @SerializedName("community")                        val community: Community,
    @SerializedName("counts")                           val counts: CommentCounts,
    @SerializedName("creator_banned_from_community")    val creatorBannedFromCommunity: Boolean,
    @SerializedName("banned_from_community")            val bannedFromCommunity: Boolean,
    @SerializedName("creator_is_moderator")             val creatorIsMod: Boolean,
    @SerializedName("creator_is_admin")                 val creatorIsAdmin: Boolean,
    @SerializedName("subscribed")                       val subscribed: String,
    @SerializedName("saved")                            val isSaved: Boolean,
    @SerializedName("creator_blocked")                  val creatorBlocked: Boolean,
    @SerializedName("my_vote")                          val myVote: Int
)

data class CommentResponse(
    @SerializedName("comments")        val comments: List<CommentView>
)