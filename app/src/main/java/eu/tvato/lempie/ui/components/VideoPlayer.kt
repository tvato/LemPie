package eu.tvato.lempie.ui.components

import androidx.annotation.OptIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.compose.SURFACE_TYPE_TEXTURE_VIEW
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import eu.tvato.lempie.R
import eu.tvato.lempie.ui.theme.LemPieTheme
import eu.tvato.lempie.ui.theme.Theme
import androidx.core.net.toUri
import androidx.media3.common.VideoSize
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.compose.ContentFrame
import eu.tvato.lempie.utils.PlayerUtils

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer(
    videoUrl: String,
    navController: NavHostController,
    prepare: Boolean,
    modifier: Modifier = Modifier,
){
    if(videoUrl.isEmpty()) return
    val player = remember { mutableStateOf(PlayerUtils.getFreePlayer()) }

    val controlsVisible = remember { mutableStateOf(false) }
    val aspectRatio = remember {
        mutableFloatStateOf((player.value?.player?.videoFormat?.width?.toFloat() ?: 9f) / (player.value?.player?.videoFormat?.height?.toFloat() ?: 16f))
    }

    LaunchedEffect(player.value?.player) {
        player.value?.player?.addListener(object : Player.Listener{
            override fun onVideoSizeChanged(videoSize: VideoSize) {
                super.onVideoSizeChanged(videoSize)
                if(videoSize.width == 0 || videoSize.height == 0){
                    aspectRatio.floatValue = 9f / 16f
                }else{
                    aspectRatio.floatValue = videoSize.width.toFloat() / videoSize.height.toFloat()
                }
            }
        })
    }

    LaunchedEffect(prepare) {
        if(prepare) {
            player.value?.player?.setMediaItem(MediaItem.fromUri(videoUrl.toUri()))
            player.value?.player?.repeatMode = Player.REPEAT_MODE_ONE
            player.value?.player?.volume = 0f
            player.value?.player?.prepare()
            player.value?.player?.playWhenReady = true
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            PlayerUtils.freePlayer(player.value?.index)
        }
    }

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = modifier
            .fillMaxSize()
            .padding(start = 10.dp, top = 10.dp, bottom = 10.dp, end = 10.dp)
            .aspectRatio(aspectRatio.floatValue)
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
            player = player.value?.player,
            contentScale = ContentScale.FillWidth,
            modifier = modifier
                .fillMaxSize()
                .align(Alignment.Center),
        )
        VideoControls(
            player = player.value?.player,
            controlsVisible = controlsVisible,
            navController = navController,
            videoUrl = videoUrl
        )

    }
}

@Composable
fun FullscreenImage(
    imageUrl: String,
    dismiss: () -> Unit
){
    Dialog(     // TODO() Maybe use something else :D
        onDismissRequest = dismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            usePlatformDefaultWidth = false
        )
    ){
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Composable
fun VideoControls(
    player: ExoPlayer?,
    controlsVisible: MutableState<Boolean>,
    navController: NavHostController,
    videoUrl: String,
    modifier: Modifier = Modifier
){
    val isPlaying = remember { mutableStateOf(player?.isPlaying ?: false) }
    val isMuted = remember { mutableStateOf(player?.volume == 0f) }
    val currentPosition = remember { mutableLongStateOf(player?.currentPosition ?: 0L) }

    if(controlsVisible.value) Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 10.dp)
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    if(isMuted.value) player?.volume = 1f else player?.volume = 0f
                    isMuted.value = !isMuted.value
                }
            ) {
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
                    if(isPlaying.value) player?.pause() else player?.play()
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
                onClick = {
                    navController.navigate("FullscreenMedia/${videoUrl}")
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.comment),
                    contentDescription = "Play/Pause",
                    tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                    modifier = modifier.size(36.dp)
                )
            }
        }
        Slider(
            value = currentPosition.longValue.toFloat(),
            onValueChange = {  }
        )
    }
}

@Preview
@Composable
fun VideoPlayerPreview(
    modifier: Modifier = Modifier
){
    val controlsVisible = remember { mutableStateOf(true) }

    LemPieTheme(theme = Theme.Dark) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = modifier
                .fillMaxSize()
                .padding(start = 5.dp, top = 10.dp, bottom = 10.dp, end = 5.dp)
                .clickable { controlsVisible.value = !controlsVisible.value }
        ){
            ContentFrame(
                player = null,
                contentScale = ContentScale.FillWidth,
                surfaceType = SURFACE_TYPE_TEXTURE_VIEW,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(16f / 9f)
                    .align(Alignment.Center),
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
    val isPlaying = remember { mutableStateOf(false) }
    val isMuted = remember { mutableStateOf(true) }

    if(controlsVisible.value) Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 10.dp)
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    isMuted.value = !isMuted.value
                }
            ) {
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
            ) {
                Icon(
                    painter = painterResource(R.drawable.comment),
                    contentDescription = "Play/Pause",
                    tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                    modifier = modifier.size(36.dp)
                )
            }
        }
        Slider(
            value = 0.5f,
            onValueChange = {}
        )
    }
}