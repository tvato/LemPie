package eu.tvato.lempie.ui.previewdata

import eu.tvato.lempie.community.Community
import eu.tvato.lempie.community.CommunityCounts
import eu.tvato.lempie.community.CommunityView

val previewCommunities = listOf(
    Community(
        id = 1, name = "Test Community 1", title = "Test community 1", description = null,
        isRemoved = false, published = "2022-07-09T17:56:21.988295Z", updated = null, isDeleted = false,
        isNsfw = false, actorId = "https://voyager.lemmy.ml/c/test_comm_1", isLocal = false, iconUrl = null, bannerUrl = null,
        postingRestrictedToMods = false, instanceId = 1, visibility = "", isHidden = false
    ),
    Community(
        id = 2, name = "Test Community 2", title = "Test community 2", description = null,
        isRemoved = false, published = "2022-07-09T17:56:21.988295Z", updated = null, isDeleted = false,
        isNsfw = false, actorId = "", isLocal = false, iconUrl = null, bannerUrl = null,
        postingRestrictedToMods = false, instanceId = 1, visibility = "", isHidden = false,
    )
)

val previewCommunityViews = listOf(
    CommunityView(
        community = previewCommunities[0], subscribed = "", isBlocked = false,
        counts = CommunityCounts(communityId = 1, subscriberCount = 105, postCount = 20, commentCount = 55, published = "",
            activeUsersDay = 5, activeUsersWeek = 11, activeUsersMonth = 20, activeUsers6Month = 44, localSubscribersCount = 44),
        bannedFromCommunity = false
    ),
    CommunityView(
        community = previewCommunities[1], subscribed = "", isBlocked = false,
        counts = CommunityCounts(communityId = 2, subscriberCount = 105, postCount = 20, commentCount = 55, published = "",
            activeUsersDay = 5, activeUsersWeek = 11, activeUsersMonth = 20, activeUsers6Month = 44, localSubscribersCount = 44),
        bannedFromCommunity = false
    ),
)