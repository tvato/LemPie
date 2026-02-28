package eu.tvato.lempie.utils

import eu.tvato.lempie.comment.CommentView

object CommentUtils{
    private val comments: MutableList<CommentView> = mutableListOf()
    fun isDepth(comment: CommentView, depth: Int): Boolean {
        return comment.comment.path.split(".").size == depth
    }

    fun addAndSort(items: List<CommentView>) {
        val copyOfItems: MutableList<CommentView> = items.toMutableList()
        var depth = 2
        while(copyOfItems.isNotEmpty()){
            items.forEach{ item ->
                val pathIDs = item.comment.path.split(".")
                if(isDepth(item, depth)){
                    val parent = comments.find{
                        pathIDs[depth - 2].toInt() == it.comment.id
                    }
                    if(parent == null){
                        comments.add(item)
                        copyOfItems.remove(item)
                    }else{
                        val parentIndex = comments.indexOf(parent)
                        comments.add(parentIndex + 1, item)
                        copyOfItems.remove(item)
                    }
                }
            }
            depth += 1
        }
    }

    fun getComments() = comments
    fun clearComments() = comments.clear()
}