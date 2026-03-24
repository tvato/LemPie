package eu.tvato.lempie.utils

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer

data class PlayerItem(
    val player: ExoPlayer,
    var isAvailable: Boolean,
    val index: Int,
)

object PlayerUtils{
    private val playerPool: MutableList<PlayerItem> = mutableListOf()

    fun initializePlayerPool(context: Context){
        playerPool.add(PlayerItem(ExoPlayer.Builder(context).build(), true, 0))
        playerPool.add(PlayerItem(ExoPlayer.Builder(context).build(), true, 1))
        playerPool.add(PlayerItem(ExoPlayer.Builder(context).build(), true, 2))
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

    fun freePlayer(index: Int?){
        playerPool.forEach {
            if(it.index == index){
                it.player.clearMediaItems()
                it.isAvailable = true
            }
        }
    }
}