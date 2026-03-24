package eu.tvato.lempie.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavHostController
import eu.tvato.lempie.ui.components.VideoPlayer
import eu.tvato.lempie.ui.components.VideoPlayerPreview
import eu.tvato.lempie.ui.theme.LemPieTheme
import eu.tvato.lempie.ui.theme.Theme

@Composable
fun MediaScreen(
    url: String?,
    innerPadding: PaddingValues,
    navController: NavHostController
){
    if(url == null) return
    VideoPlayer(
        videoUrl = url,
        navController = navController,
        prepare = false,
    )
}

@Preview
@Composable
fun MediaScreenPreview(){
    LemPieTheme(theme = Theme.Dark) {
        VideoPlayerPreview()
    }
}