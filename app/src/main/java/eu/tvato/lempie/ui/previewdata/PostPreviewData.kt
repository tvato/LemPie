package eu.tvato.lempie.ui.previewdata

import eu.tvato.lempie.post.Post
import eu.tvato.lempie.post.PostCounts
import eu.tvato.lempie.post.PostView

val previewPosts: List<Post> = listOf(
    Post(id = 1, title = "Preview title 1", imageOrLink = "https://some.website.com/",
        text = "\n" +
                "\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce eleifend dui eu auctor ornare. Cras eros enim, hendrerit vel sapien vel, sagittis condimentum nulla. Vestibulum pharetra augue nec arcu fermentum elementum. Curabitur lacinia leo ac massa feugiat lobortis. Integer congue metus vitae elit fermentum molestie. Fusce nec porttitor urna. Nunc sollicitudin at ex quis finibus. Etiam vulputate lacus nec venenatis faucibus. Proin imperdiet imperdiet ullamcorper. Cras in tempus ex, pellentesque auctor metus. Vestibulum suscipit purus enim, eu egestas lacus sollicitudin sit amet. Proin varius dui ac aliquam semper. Nulla consequat fermentum porta. Etiam id ipsum erat.\n" +
                "\n" +
                "Vivamus nec blandit arcu. Vivamus lobortis eros eu nulla dignissim dignissim. Nulla laoreet velit vel faucibus porta. Vivamus ultrices enim eget ex elementum, vel aliquet mi pellentesque. Nunc luctus gravida blandit. Pellentesque odio ex, pulvinar id justo sit amet, venenatis ullamcorper ligula. Nulla et ultrices ligula, a tincidunt urna. Aliquam at nibh porta, iaculis erat nec, consequat massa.\n" +
                "\n" +
                "Duis id augue ut tortor ornare imperdiet. Quisque fermentum, dolor a tempus interdum, augue massa iaculis mauris, vel aliquam est tortor quis arcu. Morbi ullamcorper aliquam ullamcorper. Cras ipsum velit, semper faucibus orci nec, convallis facilisis lacus. Praesent in ligula non felis scelerisque commodo. Nullam eu blandit tellus. Mauris placerat dignissim mauris, id imperdiet mauris congue at. Aliquam vestibulum dapibus rhoncus. Fusce at sodales purus. Nam nec malesuada risus. Etiam a finibus enim. Cras placerat scelerisque sem vitae mattis. Proin at nunc sed lectus sagittis ullamcorper eu vitae turpis. Nulla id malesuada est. Donec venenatis scelerisque diam.\n" +
                "\n" +
                "Integer sit amet augue eget sapien faucibus varius quis nec nisi. Suspendisse sed ornare enim. Aenean porttitor ipsum massa, non egestas diam posuere a. Donec eu velit eget velit cursus eleifend. Aliquam sit amet enim lacinia, porttitor metus et, cursus arcu. Phasellus ac erat ac elit interdum facilisis nec ut arcu. Phasellus eu tempus erat, venenatis posuere quam. Praesent dictum, dui ut porttitor suscipit, diam eros malesuada ex, in porta mi neque a elit.\n" +
                "\n" +
                "Nulla consectetur lacus est, sed pulvinar enim aliquet sed. Vestibulum efficitur hendrerit felis, in rhoncus turpis lobortis sit amet. Curabitur pulvinar nisl a arcu commodo tristique vel eu nisi. Aenean hendrerit elementum quam, eu interdum diam blandit sit amet. Quisque aliquet sodales lectus. Mauris sed tortor eu felis faucibus aliquet. Fusce neque nibh, fringilla et aliquam non, imperdiet vel nunc. Praesent sodales sagittis felis in tincidunt. Cras eget dolor a orci finibus tempor. ",
        userId = 1, communityId = 1, isRemoved = false,
        isLocked = false, published = "2022-07-09T17:56:21.988295Z", updated = null,
        isDeleted = false, isNfsw = false, embedTitle = null,
        embedDescription = null, thumbnailUrl = null, postLink = "",
        isLocal = false, embedVideoUrl = null, languageId = 1,
        isFeaturedCommunity = false, isFeaturedLocal = false,
        urlContentType = "text/html", altText = null),
    Post(id = 2, title = "Preview title 2", imageOrLink = "https://www.example.com/",
        text = null, userId = 1, communityId = 1, isRemoved = false,
        isLocked = false, published = "2022-07-09T17:56:21.988295Z", updated = null,
        isDeleted = false, isNfsw = false, embedTitle = null,
        embedDescription = null, thumbnailUrl = null, postLink = "",
        isLocal = false, embedVideoUrl = null, languageId = 1,
        isFeaturedCommunity = false, isFeaturedLocal = false,
        urlContentType = "text/link", altText = null),
    Post(id = 3, title = "Preview title 3", imageOrLink = "",
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce eleifend dui eu auctor ornare. Cras eros enim, hendrerit vel sapien vel, sagittis condimentum nulla. Vestibulum pharetra augue nec arcu fermentum elementum. Curabitur lacinia leo ac massa feugiat lobortis. Integer congue metus vitae elit fermentum molestie. Fusce nec porttitor urna.", userId = 1, communityId = 1, isRemoved = false,
        isLocked = false, published = "2022-07-09T17:56:21.988295Z", updated = null,
        isDeleted = false, isNfsw = false, embedTitle = null,
        embedDescription = null, thumbnailUrl = null, postLink = "",
        isLocal = false, embedVideoUrl = null, languageId = 1,
        isFeaturedCommunity = false, isFeaturedLocal = false,
        urlContentType = "image/jpg", altText = null),
    Post(id = 4, title = "Preview title 4", imageOrLink = "https://www.morefakewebsites.com",
        text = "askljdjaslkd ajsldkaj sdlkasj dlaksjd alskd aslkdjaslkdaj sldkasj dlaksjd alksdja sldkajs dlkasjd laksdja lskdja sldkasj dlaksdj alskdja slkdjas dlkasjd laskjd alskdj aslkdjas dlkasjd", userId = 1, communityId = 1, isRemoved = false,
        isLocked = false, published = "2022-07-09T17:56:21.988295Z", updated = null,
        isDeleted = false, isNfsw = false, embedTitle = null,
        embedDescription = null, thumbnailUrl = null, postLink = "",
        isLocal = false, embedVideoUrl = null, languageId = 1,
        isFeaturedCommunity = false, isFeaturedLocal = false,
        urlContentType = "text/html", altText = null)
)

val previewPostViews = listOf(
    PostView(
        post = previewPosts[0], creator = previewUsers[0], community = previewCommunities[0], imageDetails = null,
        creatorBannedFromCommunity = false, bannedFromCommunity = false, creatorIsMod = false, creatorIsAdmin = false,
        counts = PostCounts(postId = 1, commentCount = 23, score = 22, upvotes = 30, downvotes = 8, published = "2022-07-09T17:56:21.988295Z", newestCommentTime = ""),
        subscribed = "", isSaved = false, isRead = false, isHidden = false, creatorBlocked = false, myVote = null, unreadComments = 0
    ),
    PostView(
        post = previewPosts[1], creator = previewUsers[1], community = previewCommunities[0], imageDetails = null,
        creatorBannedFromCommunity = false, bannedFromCommunity = false, creatorIsMod = false, creatorIsAdmin = false,
        counts = PostCounts(postId = 1, commentCount = 23, score = 22, upvotes = 30, downvotes = 8, published = "2022-07-09T17:56:21.988295Z", newestCommentTime = ""),
        subscribed = "", isSaved = false, isRead = false, isHidden = false, creatorBlocked = false, myVote = null, unreadComments = 0
    ),
    PostView(
        post = previewPosts[2], creator = previewUsers[2], community = previewCommunities[1], imageDetails = null,
        creatorBannedFromCommunity = false, bannedFromCommunity = false, creatorIsMod = false, creatorIsAdmin = false,
        counts = PostCounts(postId = 1, commentCount = 23, score = 22, upvotes = 30, downvotes = 8, published = "2022-07-09T17:56:21.988295Z", newestCommentTime = ""),
        subscribed = "", isSaved = false, isRead = false, isHidden = false, creatorBlocked = false, myVote = null, unreadComments = 0
    ),
    PostView(
        post = previewPosts[3], creator = previewUsers[2], community = previewCommunities[1], imageDetails = null,
        creatorBannedFromCommunity = false, bannedFromCommunity = false, creatorIsMod = false, creatorIsAdmin = false,
        counts = PostCounts(postId = 1, commentCount = 23, score = 22, upvotes = 30, downvotes = 8, published = "2022-07-09T17:56:21.988295Z", newestCommentTime = ""),
        subscribed = "", isSaved = false, isRead = false, isHidden = false, creatorBlocked = false, myVote = null, unreadComments = 0
    ),
)