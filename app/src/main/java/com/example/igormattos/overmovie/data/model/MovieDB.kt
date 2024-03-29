package com.example.igormattos.overmovie.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.igormattos.overmovie.utils.Constants

@Entity(tableName = Constants.TABLE.NAME)
data class MovieDB(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "voteAverage")
    val voteAverage: Float,
    val title: String,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String
)