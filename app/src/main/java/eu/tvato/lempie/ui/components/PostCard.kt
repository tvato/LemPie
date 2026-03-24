package eu.tvato.lempie.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import eu.tvato.lempie.R
import eu.tvato.lempie.community.Community
import eu.tvato.lempie.post.Post
import eu.tvato.lempie.post.PostView
import eu.tvato.lempie.ui.previewdata.previewPostViews
import eu.tvato.lempie.ui.theme.LemPieTheme
import eu.tvato.lempie.ui.theme.Theme
import eu.tvato.lempie.user.User
import eu.tvato.lempie.utils.Utils
import java.net.URLEncoder

@Composable
fun PostCard(
    post: PostView?,
    user: User?,
    community: Community?,
    format: String,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    limitTextRows: Boolean = false,
    noText: Boolean = false,
    noNav: Boolean = false,
    prepareVideo: Boolean = false,
){
    Card(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(bottom = 5.dp)
            .clickable {
                if(!noNav) navController.navigate("Post/${post?.post?.id}")
            },
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
    ) {
        BasicPostInfo(
            user = user,
            community = community,
            modifier = modifier,
            navController = navController
        )
        Text(
            text = post?.post?.title.toString(),
            fontSize = 24.sp,
            modifier = modifier.padding(start = 20.dp, top = 10.dp, bottom = 20.dp, end = 20.dp),
            color = MaterialTheme.colorScheme.onBackground
        )

        TagAndDateRow(post?.post, format)

        if(post?.post?.imageOrLink != null && post.post.urlContentType?.contains("image") != true)
            Text(
                text = post.post.imageOrLink.split("/")[2],
                color = MaterialTheme.colorScheme.tertiary,
                modifier = modifier
                    .padding(start = 20.dp)
                    .clickable { /* TODO() Open default browser on click */ },
            )

        if(post?.post?.urlContentType?.contains("image") ?: false) {
            AsyncImage(
                model = post.post.imageOrLink,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.Center,
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = 5.dp, top = 10.dp, bottom = 10.dp, end = 5.dp)
                    .clickable { navController.navigate("FullscreenImage/${URLEncoder.encode(post.post.imageOrLink, "UTF-8")}") }
            )
        }else if(post?.post?.urlContentType?.contains("video") ?: false){
            VideoPlayer(
                videoUrl = post.post.imageOrLink ?: "",
                navController = navController,
                prepare = prepareVideo,
            )
        }else {
            AsyncImage(
                model = post?.post?.thumbnailUrl,
                contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.FillWidth,
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = 5.dp, top = 10.dp, bottom = 10.dp, end = 5.dp)
                    .clickable { /* TODO() Open default browser on click */ },
            )
        }
        if(post?.post?.text?.isNotEmpty() ?: false && !noText) {
            Text(
                text = Utils.parseMarkdown(post.post.text, LocalContext.current),
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
fun TagAndDateRow(
    post: Post?,
    format: String,
    modifier: Modifier = Modifier){
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
            text = Utils.parseIsoDate(post?.published, format),
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
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 10.dp)
    ){
        Row{
            InteractionButton(
                res = R.drawable.upvote,
                text = post?.counts?.upvotes.toString()
            )
            InteractionButton(
                res = R.drawable.downvote,
                text = post?.counts?.downvotes.toString(),
                modifier = modifier.padding(start = 5.dp)
            )
            InteractionButton(
                res = R.drawable.comment,
                text = post?.counts?.commentCount.toString(),
                modifier = modifier.padding(start = 5.dp)
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
                modifier = modifier.padding(start = 5.dp)
            )
        }
    }
}

@Preview
@Composable
fun PostCardPreview() {
    val postView = previewPostViews[1]
    LemPieTheme(theme = Theme.Dark) {
        PostCard(
            post = postView,
            community = postView.community,
            user = postView.creator,
            navController = rememberNavController(),
            format = "MMM d, yy, HH:mm"
        )
    }
}