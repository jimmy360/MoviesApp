package com.example.moviesapp.framework.ui.common

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(private val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstsPosition = layoutManager.findFirstVisibleItemPosition()

        if(isLoading() && !isLastPage()){
            if((visibleItemCount + firstsPosition) >= totalItemCount && firstsPosition >= 0){
                loadMovies()
            }
        }
    }

    protected abstract fun loadMovies()
    abstract fun isLastPage(): Boolean
    abstract fun isLoading(): Boolean

}