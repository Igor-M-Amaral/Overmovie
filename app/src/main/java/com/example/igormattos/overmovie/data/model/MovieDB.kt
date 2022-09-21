package com.example.igormattos.overmovie.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.igormattos.overmovie.utils.Constants

@Entity(tableName = Constants.TABLE.NAME)
data class MovieDB(
    @PrimaryKey
    val id: Int,
    val poster_path: String,
    val vote_average: Float,
    val title: String,
    val backdrop_path: String
)