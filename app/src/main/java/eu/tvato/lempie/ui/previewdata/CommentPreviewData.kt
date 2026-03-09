package eu.tvato.lempie.ui.previewdata

import eu.tvato.lempie.comment.Comment
import eu.tvato.lempie.comment.CommentCounts
import eu.tvato.lempie.comment.CommentView

val previewComments = listOf(
    Comment(
        id = 5, userId = 3, postId = 1, content = "Comment 5",
        isRemoved = false, published = "2022-07-09T17:56:21.988295Z", updated = null,
        isDeleted = false, commentLink = "null", isLocal = false, path = "0.1.2.3.4.5",
        isDistinguished = false, languageId = 1
    ),
    Comment(
        id = 3, userId = 2, postId = 1, content = "\n\n\n\n\n\n\n\n\n\t     \n\n         \t\tComment 3 and some new lines after to test stripping: \n\n\n\n \n\n",
        isRemoved = false, published = "2022-07-09T17:56:21.988295Z", updated = null,
        isDeleted = false, commentLink = "null", isLocal = false, path = "0.1.2.3",
        isDistinguished = false, languageId = 1
    ),
    Comment(
        id = 1, userId = 1, postId = 1, content = "Comment 1",
        isRemoved = false, published = "2022-07-09T17:56:21.988295Z", updated = null,
        isDeleted = false, commentLink = "null", isLocal = false, path = "0.1",
        isDistinguished = false, languageId = 1
    ),
    Comment(
        id = 2, userId = 3, postId = 1, content = "Comment 2",
        isRemoved = false, published = "2022-07-09T17:56:21.988295Z", updated = null,
        isDeleted = false, commentLink = "null", isLocal = false, path = "0.1.2",
        isDistinguished = false, languageId = 1
    ),
    Comment(
        id = 4, userId = 2, postId = 1, content = "Comment 4",
        isRemoved = false, published = "2022-07-09T17:56:21.988295Z", updated = null,
        isDeleted = false, commentLink = "null", isLocal = false, path = "0.1.2.3.4",
        isDistinguished = false, languageId = 1
    ),
    Comment(
        id = 6, userId = 3, postId = 1, content = "Comment 5, some more text to test padding of comments, so it does not hug the right wall so hard",
        isRemoved = false, published = "2022-07-09T17:56:21.988295Z", updated = null,
        isDeleted = false, commentLink = "null", isLocal = false, path = "0.1.2.3.4.5.6",
        isDistinguished = false, languageId = 1
    ),
)

val previewCommentViews = listOf(
    CommentView(
        comment = previewComments[2], creator = previewUsers[0], post = previewPosts[0], community = previewCommunities[0],
        counts = CommentCounts(postId = 1, score = 12, upvotes = 15, downvotes = 3, published = "", childCounts = 0),
        creatorBannedFromCommunity = false, bannedFromCommunity = false, creatorIsMod = false, creatorIsAdmin = false,
        subscribed = "", isSaved = false, creatorBlocked = false, myVote = 0
    ),
    CommentView(
        comment = previewComments[3], creator = previewUsers[1], post = previewPosts[0], community = previewCommunities[0],
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
        comment = previewComments[4], creator = previewUsers[2], post = previewPosts[0], community = previewCommunities[0],
        counts = CommentCounts(postId = 1, score = 12, upvotes = 15, downvotes = 3, published = "", childCounts = 0),
        creatorBannedFromCommunity = false, bannedFromCommunity = false, creatorIsMod = false, creatorIsAdmin = false,
        subscribed = "", isSaved = false, creatorBlocked = false, myVote = 0
    ),
    CommentView(
        comment = previewComments[0], creator = previewUsers[2], post = previewPosts[0], community = previewCommunities[0],
        counts = CommentCounts(postId = 1, score = 12, upvotes = 15, downvotes = 3, published = "", childCounts = 0),
        creatorBannedFromCommunity = false, bannedFromCommunity = false, creatorIsMod = false, creatorIsAdmin = false,
        subscribed = "", isSaved = false, creatorBlocked = false, myVote = 0
    ),
    CommentView(
        comment = previewComments[5], creator = previewUsers[0], post = previewPosts[0], community = previewCommunities[0],
        counts = CommentCounts(postId = 1, score = 12, upvotes = 15, downvotes = 3, published = "", childCounts = 0),
        creatorBannedFromCommunity = false, bannedFromCommunity = false, creatorIsMod = false, creatorIsAdmin = false,
        subscribed = "", isSaved = false, creatorBlocked = false, myVote = 0
    )
)