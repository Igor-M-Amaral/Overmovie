package com.examplepokedex.igormattos.tvshowapp.services.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.examplepokedex.igormattos.tvshowapp.services.model.MovieDB

@Database(
    entities = [MovieDB::class],
    version = 1
)

abstract class FavoriteDatabase : RoomDatabase(){

    abstract fun getFavoriteDao(): FavoriteDao

    companion object{
        private lateinit var INSTANCE: FavoriteDatabase

        fun getDataBase(context: Context): FavoriteDatabase{
            if (!Companion::INSTANCE.isInitialized){
                synchronized(FavoriteDatabase::class){
                    INSTANCE = Room.databaseBuilder(context, FavoriteDatabase::class.java, "favoriteDB")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }

}