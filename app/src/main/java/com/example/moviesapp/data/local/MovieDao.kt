package com.example.moviesapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesapp.domain.Movie
import com.example.moviesapp.framework.ui.common.Constants

@Dao
interface MovieDao {

    @Query("SELECT * FROM ${Constants.TABLE_MOVIES}")
    fun getMovies() : LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Movie>)
}