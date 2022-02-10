package com.example.moviesapp.di

import android.app.Application
import android.content.Context
import com.example.moviesapp.data.datasources.MovieRemoteDataSource
import com.example.moviesapp.data.remote.MovieService
import com.example.moviesapp.data.local.AppDatabase
import com.example.moviesapp.data.local.MovieDao
import com.example.moviesapp.data.repository.MovieRepository
import com.example.moviesapp.framework.ui.common.Constants
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(Constants.URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)

    @Provides
    @Singleton
    fun provideMovieDao(db: AppDatabase) = db.movieDao()

    @Provides
    @Singleton
    fun provideMovieRemoteDatasource(movieservice: MovieService) = MovieRemoteDataSource(movieservice)

    @Provides
    @Singleton
    fun provideDatabse(@ApplicationContext appcontext: Context) = AppDatabase.getDataBase(appcontext)

    @Provides
    @Singleton
    fun provideRepository(remoteSource: MovieRemoteDataSource, localSource: MovieDao) = MovieRepository(remoteSource,localSource)

}