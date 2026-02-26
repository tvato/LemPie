package eu.tvato.lempie.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import eu.tvato.lempie.R
import eu.tvato.lempie.comment.Comment
import eu.tvato.lempie.utils.parseIsoDate

@Composable
fun CommentRow(
    comment: Comment?,
    username: String,
    userInstance: String,
    modifier: Modifier = Modifier,
    noPadding: Boolean = false
) {
    val paddingValue = if(noPadding) 0 else comment?.path?.split(".")?.size?.minus(2)
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .padding(
                    start = (paddingValue?.times(20)?.plus(5))?.dp ?: 0.dp,
                    end = 5.dp, top = 5.dp, bottom = 5.dp
                )
                .fillMaxWidth()
        ) {
            Text(
                text = "${username}@${if(userInstance.split("/").size > 1) userInstance.split("/")[2] else userInstance}",
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = parseIsoDate(comment?.published),
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        Text(
            text = comment?.content.toString(),
            modifier = modifier
                .padding(
                    start = (paddingValue?.times(20)?.plus(10))?.dp ?: 5.dp,
                    top = 5.dp, bottom = 5.dp
                )
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.onBackground
        )
        Row(
            modifier = modifier
                .padding(top = 5.dp, bottom = 5.dp, end = 5.dp)
                .fillMaxWidth()
        ) {
            InteractionButton(
                res = R.drawable.upvote,
                text = comment?.upvotes.toString(),
                modifier = modifier
                    .padding(
                        start = (paddingValue?.times(20)?.plus(5))?.dp ?: 0.dp
                    )
            )
            InteractionButton(
                res = R.drawable.downvote,
                text = comment?.downvotes.toString()
            )
            Spacer(modifier.weight(1f))
            InteractionButton(
                res = R.drawable.dots,
                text = null
            )
            InteractionButton(
                res = R.drawable.bookmark,
                text = null
            )
            InteractionButton(
                res = R.drawable.share,
                text = null
            )
        }
    }
}

@Composable
fun UserComment(
    comment: Comment?,
    community: String,
    communityInstance: String,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable(onClick = {
                navController.navigate("Post/${comment?.postId}")
            })
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .padding(
                    start = 5.dp,
                    end = 5.dp, top = 5.dp, bottom = 5.dp
                )
                .fillMaxWidth()
        ) {
            Text(
                text = "${community}@${if(communityInstance.split("/").size > 1) communityInstance.split("/")[2] else communityInstance}",
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = parseIsoDate(comment?.published),
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        Text(
            text = comment?.content.toString(),
            modifier = modifier
                .padding(
                    start = 5.dp,
                    top = 5.dp, bottom = 5.dp
                )
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.onBackground
        )
        Row(
            modifier = modifier
                .padding(top = 5.dp, bottom = 5.dp, end = 5.dp)
                .fillMaxWidth()
        ) {
            InteractionButton(
                res = R.drawable.upvote,
                text = comment?.upvotes.toString(),
                modifier = modifier
                    .padding(
                        start = 5.dp
                    )
            )
            InteractionButton(
                res = R.drawable.downvote,
                text = comment?.downvotes.toString()
            )
            Spacer(modifier.weight(1f))
            InteractionButton(
                res = R.drawable.dots,
                text = null
            )
            InteractionButton(
                res = R.drawable.bookmark,
                text = null
            )
            InteractionButton(
                res = R.drawable.share,
                text = null
            )
        }
    }
}