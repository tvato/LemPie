package eu.tvato.lempie.api

import eu.tvato.lempie.comment.CommentResponse
import eu.tvato.lempie.community.CommunityResponse
import eu.tvato.lempie.post.PostResponse
import eu.tvato.lempie.post.PostsResponse
import eu.tvato.lempie.user.UserContents
import eu.tvato.lempie.user.UserDetailResponse
import retrofit2.http.GET
import retrofit2.http.Query

// TODO() v3...

interface API {
    @GET("api/v4/post/list")
    suspend fun getPosts(
        @Query("type_") type: String? = "all",
        @Query("sort") sort: String? = null,
        @Query("time_range_seconds") timeRangeSeconds: String? = null,
        @Query("community_id") communityId: Int? = null,
        @Query("community_name") communityName: String? = null,
        @Query("multi_community_id") multiCommunityId: Int? = null,
        @Query("multi_community_name") multiCommunityName: String? = null,
        @Query("show_hidden") showHidden: Boolean? = null,
        @Query("show_read") showRead: Boolean? = null,
        @Query("show_nsfw") showNsfw: Boolean? = null,
        @Query("hide_media") hideMedia: Boolean? = null,
        @Query("mark_as_read") markAsRead: Boolean? = null,
        @Query("no_comments_only") noCommentsOnly: Boolean? = null,
        @Query("page_cursor") page: String? = null,
        @Query("limit") limit: Int? = null
    ): PostsResponse

    @GET("api/v4/post")
    suspend fun getPost(
        @Query("id") postId: Int? = null,
        @Query("comment_id") commentId: Int? = null
    ): PostResponse

    @GET("api/v4/comment/list")
    suspend fun getCommentsByPostId(
        @Query("post_id") postId: Int,
        @Query("page_cursor") page: String? = null
    ): CommentResponse

    @GET("api/v4/person/content")
    suspend fun getUserContent(
        @Query("limit") limit: Int? = null,
        @Query("page_cursor") page: String? = null,
        @Query("username") username: String? = null,
        @Query("person_id") userId: Int? = null,
        @Query("type_") type: String? = null
    ): UserContents

    @GET("api/v4/person")
    suspend fun getUserDetail(
        @Query("username") username: String? = null,
        @Query("person_id") userId: Int? = null
    ): UserDetailResponse

    @GET("api/v4/community")
    suspend fun getCommunity(
        @Query("name") name: String? = null,
        @Query("id") communityId: Int? = null
    ): CommunityResponse
}