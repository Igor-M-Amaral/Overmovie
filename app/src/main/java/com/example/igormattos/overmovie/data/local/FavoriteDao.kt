package com.example.igormattos.overmovie.data.local

import androidx.room.*
import com.example.igormattos.overmovie.utils.Constants
import com.example.igormattos.overmovie.data.model.MovieDB

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(movie: MovieDB)

    @Delete
    suspend fun removeMovie(movie: MovieDB)

    @Query("Delete from ${Constants.TABLE.NAME} where id=:id")
    suspend fun deleteMovieById(id: Int)

    @Query("Select * from ${Constants.TABLE.NAME}")
    suspend fun getAllFavorites(): List<MovieDB>

    @Query("SELECT EXISTS (SELECT 1 FROM ${Constants.TABLE.NAME} WHERE id = :id)")
    suspend fun favoriteExist(id: Int): Boolean

    @Query("SELECT * FROM ${Constants.TABLE.NAME} WHERE title LIKE :searchQuery || '%'")
    suspend fun searchDataBase(searchQuery: String) : List<MovieDB>
}