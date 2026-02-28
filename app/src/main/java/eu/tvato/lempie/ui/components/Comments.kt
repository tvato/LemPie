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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import eu.tvato.lempie.R
import eu.tvato.lempie.comment.CommentView
import eu.tvato.lempie.ui.previewdata.previewCommentViews
import eu.tvato.lempie.ui.theme.LemPieTheme
import eu.tvato.lempie.ui.theme.Theme
import eu.tvato.lempie.utils.parseIsoDate

@Composable
fun CommentRow(
    comment: CommentView?,
    username: String,
    userInstance: String,
    modifier: Modifier = Modifier,
    noPadding: Boolean = false
) {
    val paddingValue = if(noPadding) 0 else comment?.comment?.path?.split(".")?.size?.minus(2) ?: 0
    val startPadding = paddingValue.plus(8).times(paddingValue.plus(2).div(2)).dp
    val colors = listOf(
        // Change these colors, maybe use theme for this
        Color.Red, Color.Green, Color.Blue, Color.Yellow
    )
    val lines = modifier
        .background(MaterialTheme.colorScheme.primaryContainer)
        .drawBehind {
            for(i in 0..<paddingValue){
                val xOffset = i.times(20f) + i.plus(1).times(2f)
                drawLine(
                    color = if(i < 4) colors[i] else colors[i - 4],
                    start = Offset(x = xOffset, y = -1f),
                    end = Offset(x = xOffset, y = size.height),
                    strokeWidth = 2.dp.toPx()
                )
            }
        }
    Column(
        modifier = if(paddingValue != 0) {
                lines
            }else{
                modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    start = startPadding,
                    end = 5.dp, top = 5.dp, bottom = 5.dp
                )
        ) {
            Text(
                text = "${username}@${if(userInstance.split("/").size > 1) userInstance.split("/")[2] else userInstance}",
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = parseIsoDate(comment?.comment?.published),
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        Text(
            text = comment?.comment?.path.toString(),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = modifier.padding(start = startPadding)
        )
        Text(
            text = comment?.comment?.content.toString(),
            modifier = modifier
                .padding(
                    start = startPadding,
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
                text = comment?.counts?.upvotes.toString(),
                modifier = modifier
                    .padding(
                        start = startPadding
                    )
            )
            InteractionButton(
                res = R.drawable.downvote,
                text = comment?.counts?.downvotes.toString()
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
    comment: CommentView?,
    username: String,
    communityInstance: String,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable(onClick = {
                navController.navigate("Post/${comment?.comment?.postId}")
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
                text = "${username}@${if(communityInstance.split("/").size > 1) communityInstance.split("/")[2] else communityInstance}",
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = parseIsoDate(comment?.comment?.published),
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        Text(
            text = comment?.comment?.content.toString(),
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
                text = comment?.counts?.upvotes.toString(),
                modifier = modifier
                    .padding(
                        start = 5.dp
                    )
            )
            InteractionButton(
                res = R.drawable.downvote,
                text = comment?.counts?.downvotes.toString()
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

@Preview
@Composable
fun CommentRowPreview(){
    LemPieTheme(theme = Theme.Dark) {
        val comment = previewCommentViews[0]
        CommentRow(
            comment = comment,
            username = comment.creator.name,
            userInstance = comment.creator.actorId,
            noPadding = false
        )
    }
}

@Preview
@Composable
fun CommentRowPreview2(){
    LemPieTheme(theme = Theme.Dark) {
        val comment = previewCommentViews[1]
        CommentRow(
            comment = comment,
            username = comment.creator.name,
            userInstance = comment.creator.actorId,
            noPadding = false
        )
    }
}

@Preview
@Composable
fun CommentRowPreview3(){
    LemPieTheme(theme = Theme.Dark) {
        val comment = previewCommentViews[5]
        CommentRow(
            comment = comment,
            username = comment.creator.name,
            userInstance = comment.creator.actorId,
            noPadding = false
        )
    }
}

@Preview
@Composable
fun CommentRowPreview4(){
    LemPieTheme(theme = Theme.Dark) {
        val comment = previewCommentViews[2]
        CommentRow(
            comment = comment,
            username = comment.creator.name,
            userInstance = comment.creator.actorId,
            noPadding = false
        )
    }
}