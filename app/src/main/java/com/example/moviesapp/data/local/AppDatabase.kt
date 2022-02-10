package com.example.moviesapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviesapp.domain.Movie
import com.example.moviesapp.framework.ui.common.Constants

@Database(entities = [Movie::class], version = Constants.VERSION_DB, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        @Volatile private var instance: AppDatabase? = null

        fun getDataBase(context: Context) : AppDatabase = instance ?: synchronized(this){
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(appContext: Context) = Room.databaseBuilder(appContext,
            AppDatabase::class.java,Constants.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    }
}