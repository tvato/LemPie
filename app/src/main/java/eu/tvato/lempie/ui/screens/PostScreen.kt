package eu.tvato.lempie.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import eu.tvato.lempie.comment.CommentViewModel
import eu.tvato.lempie.community.Community
import eu.tvato.lempie.post.PostViewModel
import eu.tvato.lempie.ui.components.CommentRow
import eu.tvato.lempie.ui.components.PostCard
import eu.tvato.lempie.ui.previewdata.previewComments
import eu.tvato.lempie.ui.previewdata.previewPosts
import eu.tvato.lempie.ui.previewdata.previewUsers
import eu.tvato.lempie.ui.theme.LemPieTheme
import eu.tvato.lempie.ui.theme.Theme

@Composable
fun PostScreen(
    navController: NavHostController,
    postId: Int,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier,
){
    // TODO() There might be a better way to do all this:
    val commentViewModel: CommentViewModel = viewModel()
    val postViewModel: PostViewModel = viewModel()
    postViewModel.setPostId(postId)
    postViewModel.loadPostDetails()
    commentViewModel.setPostId(postId)
    val comments = commentViewModel.comments.collectAsLazyPagingItems()
    val postView = postViewModel.postDetail.collectAsState()
    LazyColumn(
        modifier = modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentPadding = innerPadding
    ) {
        item {
            PostCard(
                post = postView.value?.post,
                user = postView.value?.creator,
                community = postView.value?.community,
                navController = navController
            )
        }
        if(comments.itemCount > 0) items(
            count = comments.itemCount,
            key = { index -> comments[index]?.comment?.id ?: index }
        ){ index ->
            CommentRow(
                comment = comments[index]?.comment,
                username = comments[index]?.creator?.displayName ?: comments[index]?.creator?.name.toString(),
                userInstance = comments[index]?.creator?.url.toString()
            )
        }
    }
}

@Preview
@Composable
fun PostScreenPreviewDark(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
){
    LemPieTheme(theme = Theme.Dark) {
        LazyColumn(
            modifier = modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            item {
                PostCard(
                    post = previewPosts[1],
                    user = previewUsers[0],
                    community = Community(
                        id = 1, name = "Test Community", title = "Test Community", description = "null",
                        isRemoved = false, published = "Jan 22, 2026, 12:21", updated = null,
                        isDeleted = false, isNsfw = false, url = "some.url", isLocal = false,
                        postingRestrictedToMods = false, instanceId = 1, visibility = "",
                        summary = null, subscriberCount = 1, postCount = 1, commentCount = 2, activeUsersDay = 1,
                        activeUsersWeek = 1, activeUsersMonth = 1, activeUsersHalfYear = 1,
                        localSubscriberCount = 1, reportCount = 0, unresolvedReportCount = 0,
                        isLocalRemoved = false, iconUrl = null, bannerUrl = null
                    ),
                    navController = navController
                )
            }
            items(
                count = previewComments.size,
                key = { index -> previewComments[index].id }
            ) { index ->
                CommentRow(
                    comment = previewComments[index],
                    username = previewUsers[0].displayName ?: previewUsers[0].name,
                    userInstance = previewUsers[0].url,
                    modifier = modifier.background(MaterialTheme.colorScheme.primaryContainer)
                )
            }
        }
    }
}

@Preview
@Composable
fun PostScreenPreviewLight(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
){
    LemPieTheme(theme = Theme.Light) {
        LazyColumn(
            modifier = modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            item {
                PostCard(
                    post = previewPosts[0],
                    user = previewUsers[0],
                    community = Community(
                        id = 1, name = "Test Community", title = "Test Community", description = "null",
                        isRemoved = false, published = "Jan 22, 2026, 12:21", updated = null,
                        isDeleted = false, isNsfw = false, url = "some.url", isLocal = false,
                        postingRestrictedToMods = false, instanceId = 1, visibility = "",
                        summary = null, subscriberCount = 1, postCount = 1, commentCount = 2, activeUsersDay = 1,
                        activeUsersWeek = 1, activeUsersMonth = 1, activeUsersHalfYear = 1,
                        localSubscriberCount = 1, reportCount = 0, unresolvedReportCount = 0,
                        isLocalRemoved = false, iconUrl = null, bannerUrl = null
                    ),
                    navController = navController
                )
            }
            items(
                count = previewComments.size,
                key = { index -> previewComments[index].id }
            ) { index ->
                CommentRow(
                    comment = previewComments[index],
                    username = previewUsers[0].displayName ?: previewUsers[0].name,
                    userInstance = previewUsers[0].url,
                    modifier = modifier.background(MaterialTheme.colorScheme.primaryContainer)
                )
            }
        }
    }
}

@Preview
@Composable
fun PostScreenPreviewDarkGen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
){
    LemPieTheme(theme = Theme.DarkGen) {
        LazyColumn(
            modifier = modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            item {
                PostCard(
                    post = previewPosts[0],
                    user = previewUsers[0],
                    community = Community(
                        id = 1, name = "Test Community", title = "Test Community", description = "null",
                        isRemoved = false, published = "Jan 22, 2026, 12:21", updated = null,
                        isDeleted = false, isNsfw = false, url = "some.url", isLocal = false,
                        postingRestrictedToMods = false, instanceId = 1, visibility = "",
                        summary = null, subscriberCount = 1, postCount = 1, commentCount = 2, activeUsersDay = 1,
                        activeUsersWeek = 1, activeUsersMonth = 1, activeUsersHalfYear = 1,
                        localSubscriberCount = 1, reportCount = 0, unresolvedReportCount = 0,
                        isLocalRemoved = false, iconUrl = null, bannerUrl = null
                    ),
                    navController = navController
                )
            }
            items(
                count = previewComments.size,
                key = { index -> previewComments[index].id }
            ) { index ->
                CommentRow(
                    comment = previewComments[index],
                    username = previewUsers[0].displayName ?: previewUsers[0].name,
                    userInstance = previewUsers[0].url,
                    modifier = modifier.background(MaterialTheme.colorScheme.primaryContainer)
                )
            }
        }
    }
}