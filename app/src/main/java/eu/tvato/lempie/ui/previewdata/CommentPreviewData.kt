package eu.tvato.lempie.ui.previewdata

import eu.tvato.lempie.comment.Comment

val previewComments = listOf(
    Comment(
        id = 5, userId = 3, postId = 1, content = "Comment 5",
        isRemoved = false, published = "Jan 20, 2026, 07:58", updated = null,
        isDeleted = false, commentLink = "null", isLocal = false, path = "0.2.3.5",
        isDistinguished = false, languageId = 1, score = 1,
        upvotes = 8, downvotes = 11, childCount = 0, reportCount = 0,
        unresolvedReportCount = 0, federationPending = false, isLocked = false
    ),
    Comment(
        id = 3, userId = 2, postId = 1, content = "Comment 3",
        isRemoved = false, published = "Jan 19, 2026, 13:14", updated = null,
        isDeleted = false, commentLink = "null", isLocal = false, path = "0.2.3",
        isDistinguished = false, languageId = 1, score = 1,
        upvotes = 14, downvotes = 0, childCount = 0, reportCount = 0,
        unresolvedReportCount = 0, federationPending = false, isLocked = false
    ),
    Comment(
        id = 1, userId = 1, postId = 1, content = "Comment 1",
        isRemoved = false, published = "Jan 19, 2026, 12:33", updated = null,
        isDeleted = false, commentLink = "null", isLocal = false, path = "0.1",
        isDistinguished = false, languageId = 1, score = 1,
        upvotes = 25, downvotes = 12, childCount = 0, reportCount = 0,
        unresolvedReportCount = 0, federationPending = false, isLocked = false
    ),
    Comment(
        id = 2, userId = 3, postId = 1, content = "Comment 2",
        isRemoved = false, published = "Jan 19, 2026, 13:01", updated = null,
        isDeleted = false, commentLink = "null", isLocal = false, path = "0.2",
        isDistinguished = false, languageId = 1, score = 1,
        upvotes = 10, downvotes = 5, childCount = 2, reportCount = 0,
        unresolvedReportCount = 0, federationPending = false, isLocked = false
    ),
    Comment(
        id = 4, userId = 2, postId = 1, content = "Comment 4",
        isRemoved = false, published = "Jan 20, 2026, 06:43", updated = null,
        isDeleted = false, commentLink = "null", isLocal = false, path = "0.4",
        isDistinguished = false, languageId = 1, score = 1,
        upvotes = 3, downvotes = 10, childCount = 0, reportCount = 0,
        unresolvedReportCount = 0, federationPending = false, isLocked = false
    )
)