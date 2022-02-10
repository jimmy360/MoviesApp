package com.example.moviesapp.data.remote

import com.example.moviesapp.framework.ui.common.Constants
import com.example.moviesapp.domain.ResponseData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


interface MovieService {

    @Headers(
        "Authorization: Bearer ${Constants.API_TOKEN}",
        "Content-type: application/json;charset=utf-8")
    @GET("${Constants.POPULAR_MOVIES}?page={page}")
    suspend fun getPopularMovies(@Path("page") page: Int): Response<ResponseData>

}