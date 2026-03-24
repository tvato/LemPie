package eu.tvato.lempie.utils

import android.content.Context
import android.util.Log
import androidx.media3.exoplayer.ExoPlayer

data class PlayerItem(
    val player: ExoPlayer,
    var isAvailable: Boolean,
    val index: Int,
    var isFullscreen: Boolean,
)

object PlayerUtils{
    private val playerPool: MutableList<PlayerItem> = mutableListOf()

    fun initializePlayerPool(context: Context){
        playerPool.add(PlayerItem(ExoPlayer.Builder(context).build(), true, 0, false))
        playerPool.add(PlayerItem(ExoPlayer.Builder(context).build(), true, 1, false))
        playerPool.add(PlayerItem(ExoPlayer.Builder(context).build(), true, 2, false))
    }

    fun getFreePlayer(): PlayerItem? {
        playerPool.forEach {
            if(it.isAvailable){
                it.isAvailable = false
                return it
            }
        }

        return null
    }

    fun getPlayerWithIndex(index: Int): PlayerItem? {
        Log.e("dd", "Getting player with index: $index")
        return playerPool.find { it.index == index }
    }

    fun freePlayer(index: Int?){
        playerPool.forEach {
            if(it.index == index){
                it.player.clearMediaItems()
                it.isAvailable = true
                it.isFullscreen = false
            }
        }
    }
}