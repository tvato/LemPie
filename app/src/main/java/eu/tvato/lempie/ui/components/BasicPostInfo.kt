package eu.tvato.lempie.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import eu.tvato.lempie.R
import eu.tvato.lempie.community.Community
import eu.tvato.lempie.ui.previewdata.previewCommunities
import eu.tvato.lempie.ui.previewdata.previewUsers
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
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.padding(start = 20.dp, top = 5.dp, bottom = 5.dp, end = 20.dp)
    ) {
        AsyncImage(
            model = community?.iconUrl,
            contentDescription = null,
            fallback = painterResource(R.drawable.lempie_001),
            modifier = modifier
                .width(30.dp)
                .height(30.dp)
                .clip(CircleShape)
        )
        Column(
            modifier = modifier.padding(start = 10.dp)
        ){
            Text(
                text = "${community?.name.toString()}@${communityUrl}",
                color = MaterialTheme.colorScheme.primary,
                modifier = modifier.clickable(onClick = {
                    navController.navigate("Community/${community?.id}")
                })
            )
            Text(
                text = "${user?.displayName ?: user?.name.toString()}@${userUrl}",
                color = MaterialTheme.colorScheme.secondary,
                modifier = modifier.clickable(onClick = {
                    navController.navigate("Selected/${user?.id}")
                })
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