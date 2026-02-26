package eu.tvato.lempie.api

import eu.tvato.lempie.comment.CommentResponse
import eu.tvato.lempie.community.CommunityResponse
import eu.tvato.lempie.post.PostResponse
import eu.tvato.lempie.post.PostsResponse
import eu.tvato.lempie.user.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("api/v3/post/list")
    suspend fun getPosts(
        @Query("type_") type: String? = "All",
        @Query("sort") sort: String? = null,
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null,
        @Query("community_id") communityId: Int? = null,
        @Query("community_name") communityName: String? = null,
        @Query("saved_only") savedOnly: Boolean? = null,
        @Query("liked_only") likedOnly: Boolean? = null,
        @Query("disliked_only") dislikedOnly: Boolean? = null,
        @Query("show_hidden") showHidden: Boolean? = null,
        @Query("show_read") showRead: Boolean? = null,
        @Query("show_nsfw") showNsfw: Boolean? = null,
        @Query("page_cursor") pageCursor: String? = null,
    ): PostsResponse

    @GET("api/v3/post")
    suspend fun getPost(
        @Query("id") postId: Int? = null,
        @Query("comment_id") commentId: Int? = null
    ): PostResponse

    @GET("api/v3/comment/list")
    suspend fun getCommentsByPostId(
        @Query("type_") type: String? = "All",
        @Query("sort") sort: String? = null,
        @Query("max_depth") maxDepth: Int? = null,
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null,
        @Query("community_id") communityId: Int? = null,
        @Query("community_name") communityName: String? = null,
        @Query("post_id") postId: Int,
        @Query("parent_id") parentId: Int? = null,
        @Query("saved_only") savedOnly: Boolean? = null,
        @Query("liked_only") likedOnly: Boolean? = null,
        @Query("disliked_only") dislikedOnly: Boolean? = null
    ): CommentResponse

    @GET("api/v3/user")
    suspend fun getUserDetail(
        @Query("person_id") userId: Int? = null,
        @Query("username") username: String? = null,
        @Query("sort") sort: String? = null,
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null,
        @Query("community_id") communityId: Int? = null,
        @Query("saved_only") savedOnly: Boolean? = null
    ): UserResponse

    @GET("api/v3/community")
    suspend fun getCommunity(
        @Query("id") communityId: Int? = null,
        @Query("name") name: String? = null
    ): CommunityResponse
}