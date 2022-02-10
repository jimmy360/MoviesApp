package com.example.moviesapp.data.datasources

import com.example.moviesapp.data.remote.MovieService
import com.example.moviesapp.framework.ui.common.BaseDataSource
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService
) : BaseDataSource() {

   suspend fun getMovies(page: Int) = getResult { movieService.getPopularMovies(page) }

}