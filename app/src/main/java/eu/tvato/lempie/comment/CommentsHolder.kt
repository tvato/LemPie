package eu.tvato.lempie.comment

import android.util.Log

object CommentsHolder {
    private var comments: MutableList<CommentView> = mutableListOf()

    fun addToComments(commentList: List<CommentView>){
        comments += commentList.toMutableList()
        sortComments()
    }

    // 0.24175378.24176360
    fun sortComments(){
        // FUCK THIS
        val commentMap: MutableMap<String, MutableList<CommentView>> = mutableMapOf()
        val found = comments.find{
            it.comment.path == "0.123"
        }

        comments.remove(found)
        comments.add()
        comments.map {
            when(val size = it.comment.path.split(".").size){
                2 -> {
                    if(commentMap["root"]?.isEmpty() ?: true){
                        commentMap["root"] = mutableListOf(it)
                    }else{
                        commentMap["root"]?.add(it)
                    }
                }
                else -> {
                    if(commentMap["l$size"]?.isEmpty() ?: true){
                        commentMap["l$size"] = mutableListOf(it)
                    }else{
                        commentMap["l$size"]?.add(it)
                    }
                }
            }
        }
    }

    fun getComments() = comments
}