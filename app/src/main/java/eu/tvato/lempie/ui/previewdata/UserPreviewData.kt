package eu.tvato.lempie.ui.previewdata

import eu.tvato.lempie.comment.CommentCounts
import eu.tvato.lempie.comment.CommentView
import eu.tvato.lempie.post.PostCounts
import eu.tvato.lempie.post.PostView
import eu.tvato.lempie.user.User
import eu.tvato.lempie.user.UserCounts
import eu.tvato.lempie.user.UserView

val previewUsers = listOf(
    User(
        id = 1, name = "TestUser1", displayName = null, avatarUrl = null, published = "Jun 12, 2025, 12:35",
        updated = null, bio = null, isLocal = false, bannerUrl = null, isDeleted = false, matrixUserId = null,
        isBotAccount = false, instanceId = 1, actorId = "https://some.site.fi/u/testuser1", banExpires = ""
    ),
    User(
        id = 2, name = "User2Test", displayName = null, avatarUrl = null, published = "",
        updated = null, bio = null, isLocal = false, bannerUrl = null, isDeleted = false, matrixUserId = null,
        isBotAccount = false, instanceId = 1, actorId = "https://one.more.com/u/user2test", banExpires = ""
    ),
    User(
        id = 3, name = "3TestUser", displayName = null, avatarUrl = null, published = "",
        updated = null, bio = null, isLocal = false, bannerUrl = null, isDeleted = false, matrixUserId = null,
        isBotAccount = false, instanceId = 1, actorId = "https://lemmy.world/u/3TestUser", banExpires = ""
    ),
)

val previewUserViews = listOf(
    UserView(
        user = previewUsers[0],
        counts = UserCounts(1, 22, 32),
        isAdmin = false,
    ),
    UserView(
        user = previewUsers[1],
        counts = UserCounts(2, 14, 2),
        isAdmin = false,
    ),
    UserView(
        user = previewUsers[2],
        counts = UserCounts(3, 5, 11),
        isAdmin = false,
    ),
)

val previewUserContents = listOf(
    PostView(
        post = previewPosts[0], creator = previewUsers[0], community = previewCommunities[0], imageDetails = null,
        creatorBannedFromCommunity = false, bannedFromCommunity = false, creatorIsMod = false, creatorIsAdmin = false,
        counts = PostCounts(postId = 1, commentCount = 2, score = 4, upvotes = 2, downvotes = 1, published = "", newestCommentTime = ""),
        subscribed = "", isSaved = false, isRead = false, isHidden = false, creatorBlocked = false, myVote = null, unreadComments = 0
    ),
    CommentView(
        comment = previewComments[0], creator = previewUsers[0], post = previewPosts[1], community = previewCommunities[1],
        counts = CommentCounts(postId = 1, score = 3, upvotes = 4, downvotes = 1, published = "", childCounts = 0),
        creatorBannedFromCommunity = false, bannedFromCommunity = false, creatorIsMod = false, creatorIsAdmin = false,
        subscribed = "", isSaved = false, creatorBlocked = false, myVote = 0
    )
)