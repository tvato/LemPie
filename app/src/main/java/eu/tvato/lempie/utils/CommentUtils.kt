package eu.tvato.lempie.utils

import eu.tvato.lempie.comment.CommentView

object CommentUtils{
    // pagedComments holds all the comments that comes via CommentPagingSource
    // it gets cleared when PostScreen is close
    private val pagedComments: MutableList<CommentView> = mutableListOf()
    fun isDepth(comment: CommentView, depth: Int): Boolean {
        return comment.comment.path.split(".").size == depth
    }

    fun addAndSort(items: List<CommentView>): List<CommentView>{
        if(pagedComments.containsAll(items)) return listOf()

        val comments: MutableList<CommentView> = mutableListOf()
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
                        if(!pagedComments.contains(item)) pagedComments.add(item)
                        if(!comments.contains(item)) comments.add(item)
                        copyOfItems.remove(item)
                    }else{
                        val parentIndex = comments.indexOf(parent)
                        if(!pagedComments.contains(item)) pagedComments.add(item)
                        if(!comments.contains(item)) comments.add(parentIndex + 1, item)
                        copyOfItems.remove(item)
                    }
                }
            }
            depth += 1
        }

        return comments
    }

    fun clearComments() = pagedComments.clear()
}