package com.example.moviesapp.data.repository

import com.example.moviesapp.data.datasources.MovieRemoteDataSource
import com.example.moviesapp.data.local.MovieDao
import com.example.moviesapp.usecases.DataAccessStrategy
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieDao
){
    //Get all movies and save in local database
    fun getMovies(page: Int) = DataAccessStrategy.performGetOperation(
        databaseQuery = { localDataSource.getMovies() },
        networkCall = {  remoteDataSource.getMovies(page) },
        saveCallResult = { localDataSource.insertAll(it.results)}
    )

    /**
     * Return all popular movies for page
     */
    /*
    fun getmovies(page: Int): MutableLiveData<ResponseData> {

        val mutable : MutableLiveData<ResponseData> = MutableLiveData()

        movieRemoteDataSource.getPopularMovies(page).enqueue(object : Callback<ResponseData> {
            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                if(response.isSuccessful){
                    val data = response.body()
                    mutable.value = data
                }else{
                    mutable.value = null
                }
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                mutable.value = null
            }
        })

        return mutable
    }
     */
}