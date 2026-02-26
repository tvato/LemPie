package eu.tvato.lempie.mod

import com.google.gson.annotations.SerializedName
import eu.tvato.lempie.community.Community
import eu.tvato.lempie.user.User

data class Moderator(
    @SerializedName("community")    val community: Community,
    @SerializedName("moderator")    val moderator: User
)