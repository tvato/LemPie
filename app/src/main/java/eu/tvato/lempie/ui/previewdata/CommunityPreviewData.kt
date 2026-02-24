package eu.tvato.lempie.ui.previewdata

import eu.tvato.lempie.community.Community

val previewCommunities = listOf(
    Community(
        id = 1, name = "Test Community 1", title = "Test community 1", description = null, isRemoved = false,
        published = "Jan 11, 2026, 12:23", updated = null, isDeleted = false, isNsfw = false, url = "https://voyager.lemmy.ml/c/test_comm_1",
        isLocal = false, iconUrl = null, bannerUrl = null, postingRestrictedToMods = false,
        instanceId = 1, visibility = "", summary = null, subscriberCount = 0, postCount = 1,
        commentCount = 5, activeUsersDay = 2, activeUsersWeek = 3, activeUsersMonth = 4,
        activeUsersHalfYear = 4, localSubscriberCount = 3, reportCount = 0,
        unresolvedReportCount = 0, isLocalRemoved = false
    ),
    Community(
        id = 2, name = "Test Community 2", title = "Test community 2", description = null, isRemoved = false,
        published = "Jan 11, 2026, 12:23", updated = null, isDeleted = false, isNsfw = false, url = "",
        isLocal = false, iconUrl = null, bannerUrl = null, postingRestrictedToMods = false,
        instanceId = 1, visibility = "", summary = null, subscriberCount = 0, postCount = 1,
        commentCount = 5, activeUsersDay = 2, activeUsersWeek = 3, activeUsersMonth = 4,
        activeUsersHalfYear = 4, localSubscriberCount = 3, reportCount = 0,
        unresolvedReportCount = 0, isLocalRemoved = false
    )
)