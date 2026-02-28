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
import eu.tvato.lempie.utils.CommentUtils
import eu.tvato.lempie.post.PostViewModel
import eu.tvato.lempie.ui.components.CommentRow
import eu.tvato.lempie.ui.components.PostCard
import eu.tvato.lempie.ui.previewdata.previewCommentViews
import eu.tvato.lempie.ui.previewdata.previewComments
import eu.tvato.lempie.ui.previewdata.previewPostViews
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

    // This needs to be called to get the comments
    commentViewModel.comments.collectAsLazyPagingItems()
    val postView = postViewModel.postDetail.collectAsState()

    // CommentUtils is just a workaround to get comments in a sorted manner
    val commentList = CommentUtils.getComments()
    LazyColumn(
        modifier = modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentPadding = innerPadding
    ) {
        item {
            PostCard(
                post = postView.value,
                user = postView.value?.creator,
                community = postView.value?.community,
                navController = navController
            )
        }
        if(commentList.isNotEmpty()) items(
            count = commentList.size,
            key = { index -> commentList[index].comment.id }
        ){ index ->
            CommentRow(
                comment = commentList[index],
                username = commentList[index].creator.displayName ?: commentList[index].creator.name,
                userInstance = commentList[index].creator.actorId
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
                    post = previewPostViews[1],
                    user = previewPostViews[1].creator,
                    community = previewPostViews[1].community,
                    navController = navController
                )
            }
            items(
                count = previewCommentViews.size,
                key = { index -> previewComments[index].id }
            ) { index ->
                CommentRow(
                    comment = previewCommentViews[index],
                    username = previewCommentViews[index].creator.displayName ?: previewCommentViews[index].creator.name,
                    userInstance = previewCommentViews[index].creator.actorId
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
                    post = previewPostViews[0],
                    user = previewPostViews[0].creator,
                    community = previewPostViews[0].community,
                    navController = navController
                )
            }
            items(
                count = previewComments.size,
                key = { index -> previewComments[index].id }
            ) { index ->
                CommentRow(
                    comment = previewCommentViews[index],
                    username = previewUsers[0].displayName ?: previewUsers[0].name,
                    userInstance = previewUsers[0].actorId,
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
                    post = previewPostViews[0],
                    user = previewPostViews[0].creator,
                    community = previewPostViews[0].community,
                    navController = navController
                )
            }
            items(
                count = previewCommentViews.size,
                key = { index -> previewCommentViews[index].comment.id }
            ) { index ->
                CommentRow(
                    comment = previewCommentViews[index],
                    username = previewUsers[0].displayName ?: previewUsers[0].name,
                    userInstance = previewUsers[0].actorId,
                    modifier = modifier.background(MaterialTheme.colorScheme.primaryContainer)
                )
            }
        }
    }
}