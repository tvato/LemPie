package eu.tvato.lempie.ui.previewdata

import eu.tvato.lempie.user.ContentHolder
import eu.tvato.lempie.user.User

val previewUsers = listOf(
    User(
        id = 1, name = "TestUser1", displayName = null, avatarUrl = null, published = "Jun 12, 2025, 12:35",
        updated = null, url = "https://some.site.fi/u/testuser1", bio = null, isLocal = false, bannerUrl = null,
        isDeleted = false, matrixUserId = null, isBotAccount = false, instanceId = 1,
        postCount = 1, commentCount = 1
    ),
    User(
        id = 2, name = "User2Test", displayName = null, avatarUrl = null, published = "",
        updated = null, url = "https://one.more.com/u/user2test", bio = null, isLocal = false, bannerUrl = null,
        isDeleted = false, matrixUserId = null, isBotAccount = false, instanceId = 1,
        postCount = 1, commentCount = 1
    ),
    User(
        id = 3, name = "3TestUser", displayName = null, avatarUrl = null, published = "",
        updated = null, url = "https://lemmy.world/u/3TestUser", bio = null, isLocal = false, bannerUrl = null,
        isDeleted = false, matrixUserId = null, isBotAccount = false, instanceId = 1,
        postCount = 1, commentCount = 1
    ),
)

val userPreviewContents = listOf(
    ContentHolder.CommentContent(
        comment = previewComments[0], creator = previewUsers[0], post = previewPosts[0], community = previewCommunities[0], communityActions = null,
        commentActions = null, personActions = null, creatorIsAdmin = false, tags = listOf(),
        canMod = false, creatorBanned = false, creatorBandExpires = null, creatorIsMod = false,
        creatorBannedFromCommunity = false, creatorCommunityBanExpires = null, type = "comment"
    ),
    ContentHolder.PostItem(
        post = previewPosts[0], creator = previewUsers[0], community = previewCommunities[0], imageDetails = null,
        communityActions = null, personActions = null, postActions = null,
        creatorIsAdmin = false, tags = listOf(), canMod = false, creatorIsBanned = false,
        banExpires = null, creatorIsMod = false, creatorBannedFromCommunity = false,
        communityBanExpires = null, type = "post"
    ),
    ContentHolder.CommentContent(
        comment = previewComments[1], creator = previewUsers[0], post = previewPosts[0], community = previewCommunities[0], communityActions = null,
        commentActions = null, personActions = null, creatorIsAdmin = false, tags = listOf(),
        canMod = false, creatorBanned = false, creatorBandExpires = null, creatorIsMod = false,
        creatorBannedFromCommunity = false, creatorCommunityBanExpires = null, type = "comment"
    ),
    ContentHolder.CommentContent(
        comment = previewComments[2], creator = previewUsers[0], post = previewPosts[0], community = previewCommunities[0], communityActions = null,
        commentActions = null, personActions = null, creatorIsAdmin = false, tags = listOf(),
        canMod = false, creatorBanned = false, creatorBandExpires = null, creatorIsMod = false,
        creatorBannedFromCommunity = false, creatorCommunityBanExpires = null, type = "comment"
    ),
    ContentHolder.CommentContent(
        comment = previewComments[3], creator = previewUsers[0], post = previewPosts[0], community = previewCommunities[0], communityActions = null,
        commentActions = null, personActions = null, creatorIsAdmin = false, tags = listOf(),
        canMod = false, creatorBanned = false, creatorBandExpires = null, creatorIsMod = false,
        creatorBannedFromCommunity = false, creatorCommunityBanExpires = null, type = "comment"
    ),
    ContentHolder.CommentContent(
        comment = previewComments[4], creator = previewUsers[0], post = previewPosts[0], community = previewCommunities[0], communityActions = null,
        commentActions = null, personActions = null, creatorIsAdmin = false, tags = listOf(),
        canMod = false, creatorBanned = false, creatorBandExpires = null, creatorIsMod = false,
        creatorBannedFromCommunity = false, creatorCommunityBanExpires = null, type = "comment"
    ),
    ContentHolder.PostItem(
        post = previewPosts[1], creator = previewUsers[0], community = previewCommunities[0], imageDetails = null,
        communityActions = null, personActions = null, postActions = null,
        creatorIsAdmin = false, tags = listOf(), canMod = false, creatorIsBanned = false,
        banExpires = null, creatorIsMod = false, creatorBannedFromCommunity = false,
        communityBanExpires = null, type = "post"
    ),
    ContentHolder.PostItem(
        post = previewPosts[2], creator = previewUsers[0], community = previewCommunities[0], imageDetails = null,
        communityActions = null, personActions = null, postActions = null,
        creatorIsAdmin = false, tags = listOf(), canMod = false, creatorIsBanned = false,
        banExpires = null, creatorIsMod = false, creatorBannedFromCommunity = false,
        communityBanExpires = null, type = "post"
    ),
)