package eu.tvato.lempie.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.compose.PlayerSurface
import androidx.media3.ui.compose.SURFACE_TYPE_TEXTURE_VIEW
import eu.tvato.lempie.R
import eu.tvato.lempie.ui.theme.LemPieTheme
import eu.tvato.lempie.ui.theme.Theme

@Composable
fun VideoPlayer(
    videoUrl: String,
    modifier: Modifier = Modifier
){
    if(videoUrl.isEmpty()) return
    val exoPlayer = ExoPlayer.Builder(LocalContext.current).build().apply {
        val mediaItem = MediaItem.fromUri(videoUrl)
        setMediaItem(mediaItem)
        prepare()
        mute()
    }

    val controlsVisible = remember { mutableStateOf(true) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(start = 5.dp, top = 10.dp, bottom = 10.dp, end = 5.dp)
            .clickable { controlsVisible.value = !controlsVisible.value }
    ){
        PlayerSurface(
            player = exoPlayer,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .align(Alignment.Center)
                .align(Alignment.Center),
            surfaceType = SURFACE_TYPE_TEXTURE_VIEW
        )
        VideoControls(
            player = exoPlayer,
            controlsVisible = controlsVisible
        )
    }
}

@Composable
fun VideoControls(
    player: ExoPlayer,
    controlsVisible: MutableState<Boolean>,
    modifier: Modifier = Modifier
){
    val isPlaying = remember { mutableStateOf(player.isPlaying) }
    val isMuted = remember { mutableStateOf(player.volume == 0f) }

    if(controlsVisible.value) Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                if(isMuted.value) player.volume = 1f else player.volume = 0f
                isMuted.value = !isMuted.value
            }
        ){
            Icon(
                painter = if(isMuted.value) painterResource(R.drawable.unmute)
                          else painterResource(R.drawable.mute),
                contentDescription = "Mute/Unmute",
                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                modifier = modifier.size(36.dp)
            )
        }
        IconButton(
            onClick = {
                if(isPlaying.value) player.pause() else player.play()
                isPlaying.value = !isPlaying.value
            }
        ) {
            Icon(
                painter = if(isPlaying.value) painterResource(R.drawable.pause)
                          else painterResource(R.drawable.play),
                contentDescription = "Play/Pause",
                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                modifier = modifier.size(36.dp)
            )
        }
        IconButton(
            onClick = { /* TODO() Make VideoPlayer go fullscreen */ }
        ){
            Icon(
                painter = painterResource(R.drawable.comment),
                contentDescription = "Play/Pause",
                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                modifier = modifier.size(36.dp)
            )
        }
    }
}

@Preview
@Composable
fun VideoPlayerPreview(
    modifier: Modifier = Modifier
){
    val controlsVisible = remember { mutableStateOf(true) }
    LemPieTheme(theme = Theme.Light) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()
                .padding(start = 5.dp, top = 10.dp, bottom = 10.dp, end = 5.dp)
                .clickable { controlsVisible.value = !controlsVisible.value }
        ) {
            PlayerSurface(
                player = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .align(Alignment.Center)
                    .align(Alignment.Center),
                //surfaceType = SURFACE_TYPE_TEXTURE_VIEW
            )
            VideoControlsPreview(
                controlsVisible = controlsVisible
            )
        }
    }
}

@Composable
fun VideoControlsPreview(
    controlsVisible: MutableState<Boolean>,
    modifier: Modifier = Modifier
){
    val isPlaying = remember { mutableStateOf(true) }
    val isMuted = remember { mutableStateOf(true) }

    if(controlsVisible.value) Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { isMuted.value = !isMuted.value }
        ){
            Icon(
                painter = if(isMuted.value) painterResource(R.drawable.unmute)
                          else painterResource(R.drawable.mute),
                contentDescription = "Mute/Unmute",
                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                modifier = modifier.size(36.dp)
            )
        }
        IconButton(
            onClick = { isPlaying.value = !isPlaying.value }
        ) {
            Icon(
                painter = if(isPlaying.value) painterResource(R.drawable.pause)
                          else painterResource(R.drawable.play),
                contentDescription = "Play/Pause",
                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                modifier = modifier.size(36.dp)
            )
        }
        IconButton(
            onClick = {}
        ){
            Icon(
                painter = painterResource(R.drawable.comment),
                contentDescription = "Play/Pause",
                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                modifier = modifier.size(36.dp)
            )
        }
    }
}