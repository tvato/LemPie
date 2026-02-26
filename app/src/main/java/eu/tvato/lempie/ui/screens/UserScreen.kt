package eu.tvato.lempie.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import eu.tvato.lempie.R
import eu.tvato.lempie.comment.CommentView
import eu.tvato.lempie.post.PostView
import eu.tvato.lempie.ui.components.PostCard
import eu.tvato.lempie.ui.components.UserComment
import eu.tvato.lempie.ui.previewdata.previewUserContents
import eu.tvato.lempie.ui.previewdata.previewUserViews
import eu.tvato.lempie.ui.theme.LemPieTheme
import eu.tvato.lempie.ui.theme.Theme
import eu.tvato.lempie.user.UserView
import eu.tvato.lempie.user.UserViewModel
import eu.tvato.lempie.utils.parseIsoDate

@Composable
fun UserScreen(
    innerPadding: PaddingValues,
    navController: NavHostController,
    userId: Int,
    modifier: Modifier = Modifier
){
    val viewModel: UserViewModel = viewModel()
    viewModel.setUserId(userId)
    val userContents = viewModel.contents.collectAsLazyPagingItems()

    val userDetails = userContents[0]?.user
    val posts = viewModel.posts.collectAsLazyPagingItems()
    val comments = viewModel.comments.collectAsLazyPagingItems()
    val moderates = viewModel.moderates.collectAsLazyPagingItems()

    var contentType by remember { mutableStateOf("All") }

    var tabIndex by remember { mutableIntStateOf(0) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        item {
            UserDetailsCard(userDetails)
        }
        item {
            ContentTab(
                changeType = { type, index ->
                    tabIndex = index
                    contentType = type
                },
                tabIndex = tabIndex
            )
        }
        items(
            count = userContents.itemCount,
            key = { index -> index }
        ){ index ->
            val pagePosts = posts[index]
            val pageComments = comments[index]
            val all = pagePosts?.plus(pageComments)
            Card(       // TODO() Remove this? Is this necessary?
                modifier = modifier.padding(bottom = 5.dp)
            ) {
                when(val item = all?.get(index)){
                    is PostView ->
                        PostCard(
                            post = item,
                            user = item.creator,
                            community = item.community,
                            navController = navController,
                            limitTextRows = true
                        )

                    is CommentView ->
                        UserComment(
                            comment = item,
                            username = item.creator.displayName ?: item.creator.name,
                            communityInstance = item.creator.actorId,
                            navController = navController
                        )
                }
            }
        }
    }
}

@Composable
fun UserDetailsCard(
    user: UserView?,
    modifier: Modifier = Modifier,
){
    ConstraintLayout(modifier = modifier.background(Color.Black)){
        val (banner, userIcon, card) = createRefs()
        if(user?.user?.bannerUrl != null) AsyncImage(
                model = user.user.bannerUrl,
                contentDescription = null,
                modifier = modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .constrainAs(banner) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.Crop
            )
        Card(
            modifier = modifier
                .fillMaxSize()
                .constrainAs(card) {
                    top.linkTo(parent.top, margin = 200.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            colors = CardDefaults.cardColors().copy(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        ) {
            Text(
                text = user?.user?.displayName ?: user?.user?.name.toString(),
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 50.dp)
            )
            Text(
                text = "${user?.user?.displayName ?: user?.user?.name}@${user?.user?.actorId?.split("/")[2]}",
                modifier = modifier.align(Alignment.CenterHorizontally)
            )
            Row(
                modifier = modifier.padding(top = 10.dp, start = 50.dp, end = 50.dp, bottom = 20.dp),
            ) {
                Text(
                    text = "Posts: ${user?.counts?.postCount}"
                )
                Spacer(modifier = modifier.weight(1f))
                Text(
                    text = "Comments: ${user?.counts?.commentCount}"
                )
            }
            Text(
                text = "Created: ${parseIsoDate(user?.user?.published)}",
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 20.dp)
            )
        }
        AsyncImage(
            model = user?.user?.avatarUrl,
            contentDescription = null,
            fallback = painterResource(R.drawable.lempie_001), // TODO() change drawable, make better one...
            modifier = modifier
                .clip(CircleShape)
                .constrainAs(userIcon) {
                    top.linkTo(parent.top, margin = 155.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Composable
fun ContentTab(
    changeType: (String, Int) -> Unit,
    tabIndex: Int,
    modifier: Modifier = Modifier
){
    val tabs = listOf("All", "Posts", "Comments")
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        PrimaryTabRow(
            selectedTabIndex = tabIndex,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            divider = {
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.surface,
                    thickness = 3.dp
                )
            },
            indicator = {
                TabRowDefaults.PrimaryIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = modifier.tabIndicatorOffset(tabIndex),
                    width = 50.dp
                )
            }
        ) {
            tabs.forEachIndexed{ index, title ->
                Tab(
                    text = { Text(text = title) },
                    selected = tabIndex == index,
                    onClick = {
                        changeType(title.lowercase(), index)
                    }
                )
            }
        }
    }
}


@Composable
fun UserScreenPreview(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(0.dp),
    navController: NavHostController = rememberNavController(),
    theme: Theme = Theme.Dark
){
    LemPieTheme(theme = theme) {
        val user = previewUserViews[0]
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            item {
                UserDetailsCard(user)
            }
            item {
                ContentTab(
                    changeType = { _, _ -> },
                    tabIndex = 0
                )
            }
            items(
                count = previewUserContents.size,
                key = { index -> index }
            ){ index ->
                Card(
                    modifier = modifier.padding(bottom = 5.dp)
                ) {
                    when(val item = previewUserContents[index]){
                        is PostView ->
                            PostCard(
                                post = item,
                                user = item.creator,
                                community = item.community,
                                navController = navController,
                                limitTextRows = true
                            )

                        is CommentView ->
                            UserComment(
                                comment = item,
                                username = item.creator.displayName ?: item.creator.name,
                                communityInstance = item.creator.actorId,
                                navController = navController
                            )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun UserScreenPreviewDark(){
    UserScreenPreview(theme = Theme.Dark)
}

@Preview
@Composable
fun UserScreenPreviewLight(){
    UserScreenPreview(theme = Theme.Light)
}

@Preview
@Composable
fun UserScreenPreviewDarkGen(){
    UserScreenPreview(theme = Theme.DarkGen)
}