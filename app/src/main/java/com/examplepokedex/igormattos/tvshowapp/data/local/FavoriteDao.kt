package com.examplepokedex.igormattos.tvshowapp.data.local

import androidx.room.*
import com.examplepokedex.igormattos.tvshowapp.utils.Constants
import com.examplepokedex.igormattos.tvshowapp.data.model.MovieDB

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(movie: MovieDB)

    @Delete
    fun removeMovie(movie: MovieDB)

    @Query("Delete from ${Constants.TABLE.NAME} where id=:id")
    fun deleteMovieById(id: Int)

    @Query("Select * from ${Constants.TABLE.NAME}")
    fun getAllFavorites() : List<MovieDB>

    @Query("SELECT EXISTS (SELECT 1 FROM ${Constants.TABLE.NAME} WHERE id = :id)")
    fun favoriteExist(id: Int): Boolean
}