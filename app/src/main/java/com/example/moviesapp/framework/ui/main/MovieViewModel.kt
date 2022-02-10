package com.example.moviesapp.framework.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.data.repository.MovieRepository
import com.example.moviesapp.domain.Movie
import com.example.moviesapp.framework.ui.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(repository: MovieRepository):
    ViewModel() {

     var currentPage: Int = 1

     val movies : LiveData<Resource<List<Movie>>> = repository.getMovies(currentPage)

    /*
    //Return LiveData of list movies
    fun getMovies(page: Int): LiveData<ResponseData> {
        return movieRepository.getmovies(page)
    }
     */
}