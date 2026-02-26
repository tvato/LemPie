package eu.tvato.lempie.user

import com.google.gson.annotations.SerializedName
import eu.tvato.lempie.comment.CommentView
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