package com.example.moviesapp.domain

import com.google.gson.annotations.SerializedName

data class ResponseData(@SerializedName("results") val results: List<Movie>)