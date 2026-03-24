package eu.tvato.lempie.ui.components

import android.graphics.drawable.VectorDrawable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import eu.tvato.lempie.R
import eu.tvato.lempie.community.Community
import eu.tvato.lempie.ui.previewdata.previewCommunityViews
import eu.tvato.lempie.ui.previewdata.previewUserViews
import eu.tvato.lempie.ui.theme.LemPieTheme
import eu.tvato.lempie.user.User

@Composable
fun BasicPostInfo(
    community: Community?,
    user: User?,
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    val communityUrl = if(community?.actorId.toString().split("/").size > 1) {
        community?.actorId.toString().split("/")[2]
    } else {
        community?.actorId.toString()
    }

    val userUrl = if(user?.actorId.toString().split("/").size > 1) {
        user?.actorId.toString().split("/")[2]
    } else {
        user?.actorId.toString()
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.horizontalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.secondaryContainer,
                        MaterialTheme.colorScheme.tertiaryContainer
                    )
                ),
                CardDefaults.shape
            )
            .padding(start = 20.dp, top = 5.dp, bottom = 5.dp, end = 20.dp)
    ) {
        AsyncImage(
            model = community?.iconUrl,
            contentDescription = null,
            fallback = painterResource(R.drawable.lempie_no_circle),
            modifier = if(community?.iconUrl != null) {
                modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .clickable { navController.navigate("Community/${community?.id}") }
            }else {
                val colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.secondary
                )
                modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .clickable { navController.navigate("Community/${community?.id}") }
                    .graphicsLayer(alpha = 0.99f)
                    .drawWithCache {
                        onDrawWithContent {
                            drawContent()
                            drawRect(
                                Brush.horizontalGradient(colors),
                                blendMode = BlendMode.SrcAtop
                            )
                        }
                    }
            }
        )
        Column(
            modifier = modifier.padding(start = 10.dp)
        ){
            Text(
                text = "${community?.name.toString()}@${communityUrl}",
                color = MaterialTheme.colorScheme.primary,
                modifier = modifier
                    .clickable{ navController.navigate("Community/${community?.id}") }
            )
            Text(
                text = "${user?.displayName ?: user?.name.toString()}@${userUrl}",
                color = MaterialTheme.colorScheme.secondary,
                modifier = modifier
                    .clickable{ navController.navigate("User/${user?.id}") }
            )
        }

    }
}

@PreviewLightDark
@Composable
fun  BasicPostInfoPreview(){
    LemPieTheme {
        BasicPostInfo(
            community = previewCommunityViews[1].community,
            user = previewUserViews[0].user,
            navController = rememberNavController()
        )
    }
}