package com.example.moviesapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesapp.framework.ui.common.Constants
import com.google.gson.annotations.SerializedName

@Entity(tableName = Constants.TABLE_MOVIES)
data class Movie (
    @PrimaryKey
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val vote_average: Double,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("original_title") val original_title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("original_language") val original_language: String,
    @SerializedName("vote_count") val vote_count: Float
 )