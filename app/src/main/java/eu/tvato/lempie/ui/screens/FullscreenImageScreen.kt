package eu.tvato.lempie.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import java.net.URLDecoder

@Composable
fun FullscreenImageScreen(
    imageUrl: String?,
    modifier: Modifier = Modifier
){
    AsyncImage(
        model = URLDecoder.decode(imageUrl, "UTF-8"),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        alignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    )
}