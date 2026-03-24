package eu.tvato.lempie.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.media3.ui.compose.ContentFrame
import androidx.navigation.NavHostController
import eu.tvato.lempie.ui.components.VideoControls
import eu.tvato.lempie.ui.components.VideoPlayerPreview
import eu.tvato.lempie.ui.theme.LemPieTheme
import eu.tvato.lempie.ui.theme.Theme
import eu.tvato.lempie.utils.PlayerUtils

@Composable
fun FullscreenVideoScreen(
    playerIndex: Int?,
    innerPadding: PaddingValues,
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    if(playerIndex == null) return
    val exoPlayer = PlayerUtils.getPlayerWithIndex(playerIndex)
    val controlsVisible = remember { mutableStateOf(false) }

    BackHandler {
        exoPlayer?.isFullscreen = false
        navController.navigateUp()
    }

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = modifier
            .fillMaxSize()
            .aspectRatio(9f / 16f)
            .clickable { controlsVisible.value = !controlsVisible.value }
//            .onGloballyPositioned { coordinates ->
//                val windowBounds = coordinates.boundsInWindow()
//                val visibleHeight = windowBounds.height
//                val height = coordinates.size.height
//                val visible = visibleHeight > height * 0.5f
//                val distFromTop = windowBounds.top
//
//                isVisible.value = visible
//                //Log.e("dd", "Post with ID $postId is visible: $visible")
//
//                //PlayerUtils.updateVideoDistances(postId, distFromTop, visible)
//            }
    ) {
        ContentFrame(
            player = exoPlayer?.player,
            contentScale = ContentScale.FillWidth,
            modifier = modifier
                .fillMaxSize()
                .align(Alignment.Center),
        )
        VideoControls(
            player = exoPlayer,
            controlsVisible = controlsVisible,
            navController = navController
        )

    }
}

@Preview
@Composable
fun MediaScreenPreview(){
    LemPieTheme(theme = Theme.Dark) {
        VideoPlayerPreview()
    }
}