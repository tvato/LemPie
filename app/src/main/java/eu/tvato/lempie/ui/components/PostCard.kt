package eu.tvato.lempie.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import eu.tvato.lempie.R
import eu.tvato.lempie.community.Community
import eu.tvato.lempie.post.Post
import eu.tvato.lempie.post.PostView
import eu.tvato.lempie.ui.previewdata.previewCommunities
import eu.tvato.lempie.ui.previewdata.previewPostViews
import eu.tvato.lempie.ui.previewdata.previewPosts
import eu.tvato.lempie.ui.previewdata.previewUsers
import eu.tvato.lempie.ui.theme.LemPieTheme
import eu.tvato.lempie.user.User
import eu.tvato.lempie.utils.parseIsoDate

@Composable
fun PostCard(
    post: PostView?,
    user: User?,
    community: Community?,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    limitTextRows: Boolean = false,
    noText: Boolean = false
){
    Card(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(bottom = 5.dp)
            .clickable(onClick = {
                navController.navigate("Post/${post?.post?.id}")
            }),
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 20.dp
        ),
        border = null
    ) {
        BasicPostInfo(
            user = user,
            community = community,
            modifier = modifier,
            navController = navController
        )
        Text(
            text = post?.post?.title.toString(),
            fontSize = 30.sp,
            modifier = modifier.padding(start = 20.dp, top = 10.dp, bottom = 20.dp, end = 20.dp),
            color = MaterialTheme.colorScheme.onBackground
        )

        TagAndDateRow(post?.post)

        if(post?.post?.imageOrLink != null && post.post.urlContentType?.contains("image") != true) Text(
            text = post.post.imageOrLink.split("/")[2],
            modifier = modifier.padding(start = 20.dp),
            color = MaterialTheme.colorScheme.tertiary
        )

        if(post?.post?.urlContentType?.contains("image") ?: false){
            AsyncImage(
                model = post.post.imageOrLink,
                contentDescription = null,
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 20.dp, top =10.dp, bottom = 10.dp, end = 20.dp)
                    .fillMaxHeight(0.5f)
            )
        }else if(post?.post?.imageOrLink == null){
            AsyncImage(
                model = post?.post?.thumbnailUrl,
                contentDescription = null,
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 20.dp, top =10.dp, bottom = 10.dp, end = 20.dp)
                    .fillMaxHeight(0.5f)
            )
        }
        if(post?.post?.text?.isNotEmpty() ?: false && !noText) {
            Text(
                text = post.post.text,
                modifier = modifier.padding(start = 20.dp, top = 10.dp, end = 20.dp, bottom = 20.dp),
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = if(limitTextRows) 5 else Int.MAX_VALUE,
                overflow = if(limitTextRows) TextOverflow.Ellipsis else TextOverflow.Clip
            )
        }

        ButtonsRow(post, modifier)
    }
}

@Composable
fun TagAndDateRow(post: Post?, modifier: Modifier = Modifier){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Tag(
            text = if(post?.urlContentType?.contains("image") ?: false) {
                "IMAGE"
            }else if(post?.urlContentType?.contains("video") ?: false){
                "VIDEO"
            }else if(post?.urlContentType == null && post?.imageOrLink != null){
                "LINK"
            }else{
                "TEXT"
            }
        )
        Text(
            text = parseIsoDate(post?.published),
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}

@Composable
fun ButtonsRow(post: PostView?, modifier: Modifier = Modifier){
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp, top = 10.dp)
    ){
        Row{
            InteractionButton(
                res = R.drawable.upvote,
                text = post?.counts?.upvotes.toString()
            )
            InteractionButton(
                res = R.drawable.downvote,
                text = post?.counts?.downvotes.toString(),
                modifier = modifier.padding(start = 10.dp)
            )
            InteractionButton(
                res = R.drawable.comment,
                text = post?.counts?.commentCount.toString(),
                modifier = modifier.padding(start = 10.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Row{
            InteractionButton(
                res = R.drawable.bookmark,
                text = null,
            )
            InteractionButton(
                res = R.drawable.share,
                text = null,
                modifier = modifier.padding(start = 10.dp)
            )
        }
    }
}

@PreviewLightDark
@Composable
fun PostCardPreview() {
    LemPieTheme {
        PostCard(
            post = previewPostViews[2],
            community = previewPostViews[2].community,
            user = previewPostViews[2].creator,
            navController = rememberNavController()
        )
    }
}