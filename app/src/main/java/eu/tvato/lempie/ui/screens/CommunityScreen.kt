package eu.tvato.lempie.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import eu.tvato.lempie.community.CommunityView
import eu.tvato.lempie.community.CommunityViewModel
import eu.tvato.lempie.ui.components.PostCard
import eu.tvato.lempie.ui.previewdata.previewCommunityViews
import eu.tvato.lempie.ui.previewdata.previewPostViews
import eu.tvato.lempie.ui.previewdata.previewUsers
import eu.tvato.lempie.ui.theme.LemPieTheme
import eu.tvato.lempie.ui.theme.Theme
import eu.tvato.lempie.utils.parseIsoDate

@Composable
fun CommunityScreen(
    navController: NavHostController,
    communityId: Int,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier
){
    val communityViewModel: CommunityViewModel = viewModel()
    communityViewModel.setCommunityId(communityId)
    communityViewModel.loadCommunity()
    val community = communityViewModel.community.collectAsState()
    val posts = communityViewModel.posts.collectAsLazyPagingItems()
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        item {
            CommunityDetailsCard(
                community = community.value?.communityView
            )
        }

        if(posts.itemCount > 0) items(
            count = posts.itemCount,
            key = { index -> index }
        ) { index ->
            PostCard(
                post = posts[index],
                user = posts[index]?.creator,
                community = community.value?.communityView?.community,
                navController = navController,
                limitTextRows = true
            )
        }
    }
}

@Composable
fun CommunityDetailsCard(
    community: CommunityView?,
    modifier: Modifier = Modifier,
){
    ConstraintLayout(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(bottom = 50.dp)
    ){
        val (banner, userIcon, card) = createRefs()
        if(community?.community?.bannerUrl != null) AsyncImage(
            model = community.community.bannerUrl,
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
                containerColor = MaterialTheme.colorScheme.surface,     // TODO() Change these? And also in UserScreen?
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        ) {
            Text(
                text = community?.community?.name.toString(),
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 50.dp)
            )
            Text(
                text = "${community?.community?.name}@${community?.community?.actorId?.split("/")[2]}",
                modifier = modifier.align(Alignment.CenterHorizontally)
            )
            Row(
                modifier = modifier.padding(top = 10.dp, start = 50.dp, end = 50.dp, bottom = 10.dp)
            ){
                Text("Subscribers: ${community?.counts?.subscriberCount}")
                Spacer(modifier = modifier.weight(1f))
                Text("Active users: ${community?.counts?.activeUsersMonth}")
            }
            Row(
                modifier = modifier.padding(top = 10.dp, start = 50.dp, end = 50.dp, bottom = 20.dp),
            ) {
                Text(
                    text = "Posts: ${community?.counts?.postCount}"
                )
                Spacer(modifier = modifier.weight(1f))
                Text(
                    text = "Comments: ${community?.counts?.commentCount}"
                )
            }
            Text(
                text = "Created: ${parseIsoDate(community?.community?.published)}",
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 20.dp)
            )
        }
        AsyncImage(
            model = community?.community?.iconUrl,
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


@Preview
@Composable
fun CommunityScreenPreview(
    theme: Theme = Theme.Dark
){
    val community = previewCommunityViews[0]
    val posts = previewPostViews
    val users = previewUsers
    LemPieTheme(theme = theme) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingValues())
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            item {
                CommunityDetailsCard(
                    community = community
                )
            }

            items(
                count = posts.size,
                key = { index -> index }
            ) { index ->
                PostCard(
                    post = posts[index],
                    user = users[index],
                    community = community.community,
                    navController = rememberNavController(),
                    limitTextRows = true
                )
            }
        }
    }
}
