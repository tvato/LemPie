package eu.tvato.lempie.ui.previewdata

import eu.tvato.lempie.comment.Comment
import eu.tvato.lempie.comment.CommentCounts
import eu.tvato.lempie.comment.CommentView

val previewComments = listOf(
    Comment(
        id = 5, userId = 3, postId = 1, content = "Comment 5",
        isRemoved = false, published = "Jan 20, 2026, 07:58", updated = null,
        isDeleted = false, commentLink = "null", isLocal = false, path = "0.2.3.5",
        isDistinguished = false, languageId = 1
    ),
    Comment(
        id = 3, userId = 2, postId = 1, content = "Comment 3",
        isRemoved = false, published = "Jan 19, 2026, 13:14", updated = null,
        isDeleted = false, commentLink = "null", isLocal = false, path = "0.2.3",
        isDistinguished = false, languageId = 1
    ),
    Comment(
        id = 1, userId = 1, postId = 1, content = "Comment 1",
        isRemoved = false, published = "Jan 19, 2026, 12:33", updated = null,
        isDeleted = false, commentLink = "null", isLocal = false, path = "0.1",
        isDistinguished = false, languageId = 1
    ),
    Comment(
        id = 2, userId = 3, postId = 1, content = "Comment 2",
        isRemoved = false, published = "Jan 19, 2026, 13:01", updated = null,
        isDeleted = false, commentLink = "null", isLocal = false, path = "0.2",
        isDistinguished = false, languageId = 1
    ),
    Comment(
        id = 4, userId = 2, postId = 1, content = "Comment 4",
        isRemoved = false, published = "Jan 20, 2026, 06:43", updated = null,
        isDeleted = false, commentLink = "null", isLocal = false, path = "0.4",
        isDistinguished = false, languageId = 1
    )
)

val previewCommentViews = listOf(
    CommentView(
        comment = previewComments[0], creator = previewUsers[0], post = previewPosts[0], community = previewCommunities[0],
        counts = CommentCounts(postId = 1, score = 12, upvotes = 15, downvotes = 3, published = "", childCounts = 0),
        creatorBannedFromCommunity = false, bannedFromCommunity = false, creatorIsMod = false, creatorIsAdmin = false,
        subscribed = "", isSaved = false, creatorBlocked = false, myVote = 0
    ),
    CommentView(
        comment = previewComments[1], creator = previewUsers[1], post = previewPosts[0], community = previewCommunities[0],
        counts = CommentCounts(postId = 1, score = 12, upvotes = 15, downvotes = 3, published = "", childCounts = 0),
        creatorBannedFromCommunity = false, bannedFromCommunity = false, creatorIsMod = false, creatorIsAdmin = false,
        subscribed = "", isSaved = false, creatorBlocked = false, myVote = 0
    ),
    CommentView(
        comment = previewComments[2], creator = previewUsers[1], post = previewPosts[0], community = previewCommunities[0],
        counts = CommentCounts(postId = 1, score = 12, upvotes = 15, downvotes = 3, published = "", childCounts = 0),
        creatorBannedFromCommunity = false, bannedFromCommunity = false, creatorIsMod = false, creatorIsAdmin = false,
        subscribed = "", isSaved = false, creatorBlocked = false, myVote = 0
    ),
    CommentView(
        comment = previewComments[3], creator = previewUsers[2], post = previewPosts[0], community = previewCommunities[0],
        counts = CommentCounts(postId = 1, score = 12, upvotes = 15, downvotes = 3, published = "", childCounts = 0),
        creatorBannedFromCommunity = false, bannedFromCommunity = false, creatorIsMod = false, creatorIsAdmin = false,
        subscribed = "", isSaved = false, creatorBlocked = false, myVote = 0
    ),
    CommentView(
        comment = previewComments[4], creator = previewUsers[2], post = previewPosts[0], community = previewCommunities[0],
        counts = CommentCounts(postId = 1, score = 12, upvotes = 15, downvotes = 3, published = "", childCounts = 0),
        creatorBannedFromCommunity = false, bannedFromCommunity = false, creatorIsMod = false, creatorIsAdmin = false,
        subscribed = "", isSaved = false, creatorBlocked = false, myVote = 0
    ),
)