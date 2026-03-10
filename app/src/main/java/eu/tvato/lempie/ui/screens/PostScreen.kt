package eu.tvato.lempie.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import eu.tvato.lempie.ui.components.CommentRow
import eu.tvato.lempie.ui.components.PostCard
import eu.tvato.lempie.ui.previewdata.previewCommentViews
import eu.tvato.lempie.ui.previewdata.previewPostViews
import eu.tvato.lempie.ui.previewdata.previewUsers
import eu.tvato.lempie.ui.screens.viewmodel.PostViewModel
import eu.tvato.lempie.ui.theme.LemPieTheme
import eu.tvato.lempie.ui.theme.Theme

@Composable
fun PostScreen(
    navController: NavHostController,
    postId: Int,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier,
){
    val owner = LocalViewModelStoreOwner.current ?: error("No ViewModelStoreOwner found")
    val viewModel: PostViewModel = ViewModelProvider(
        owner = owner,
        factory = PostViewModel.PostViewModelFactory(
            context = LocalContext.current,
            postId = postId
        )
    )[PostViewModel::class.java]

    val comments = viewModel.comments.collectAsLazyPagingItems()
    val postView = viewModel.postDetail.collectAsState()
    val format = viewModel.datetimeFormat.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentPadding = innerPadding
    ) {
        item {
            PostCard(
                post = postView.value,
                user = postView.value?.creator,
                community = postView.value?.community,
                navController = navController,
                format = format.value,
                noNav = true
            )
        }

        items(
            count = comments.itemCount,
            key = { index -> comments[index]?.comment?.id ?: index }
        ) { index ->
            CommentRow(
                comment = comments[index],
                username = comments[index]?.creator?.displayName ?: comments[index]?.creator?.name.toString(),
                userInstance = comments[index]?.creator?.actorId.toString(),
                format = format.value
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
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            item {
                PostCard(
                    post = previewPostViews[1],
                    user = previewPostViews[1].creator,
                    community = previewPostViews[1].community,
                    navController = navController,
                    format = "MMM d, yy, HH:mm"
                )
            }
            items(
                count = previewCommentViews.size,
                key = { index -> previewCommentViews[index].comment.id }
            ) { index ->
                CommentRow(
                    comment = previewCommentViews[index],
                    username = previewCommentViews[index].creator.displayName ?: previewCommentViews[index].creator.name,
                    userInstance = previewCommentViews[index].creator.actorId,
                    format = "MMM d, yy, HH:mm"
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
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            item {
                PostCard(
                    post = previewPostViews[0],
                    user = previewPostViews[0].creator,
                    community = previewPostViews[0].community,
                    navController = navController,
                    format = "MMM d, yy, HH:mm"
                )
            }
            items(
                count = previewCommentViews.size,
                key = { index -> previewCommentViews[index].comment.id }
            ) { index ->
                CommentRow(
                    comment = previewCommentViews[index],
                    username = previewCommentViews[0].creator.displayName ?: previewUsers[0].name,
                    userInstance = previewCommentViews[0].creator.actorId,
                    modifier = modifier.background(MaterialTheme.colorScheme.primaryContainer),
                    format = "MMM d, yy, HH:mm"
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
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            item {
                PostCard(
                    post = previewPostViews[0],
                    user = previewPostViews[0].creator,
                    community = previewPostViews[0].community,
                    navController = navController,
                    format = "MMM d, yy, HH:mm"
                )
            }
            items(
                count = previewCommentViews.size,
                key = { index -> previewCommentViews[index].comment.id }
            ) { index ->
                CommentRow(
                    comment = previewCommentViews[index],
                    username = previewCommentViews[0].creator.displayName ?: previewUsers[0].name,
                    userInstance = previewCommentViews[0].creator.actorId,
                    modifier = modifier.background(MaterialTheme.colorScheme.primaryContainer),
                    format = "MMM d, yy, HH:mm"
                )
            }
        }
    }
}