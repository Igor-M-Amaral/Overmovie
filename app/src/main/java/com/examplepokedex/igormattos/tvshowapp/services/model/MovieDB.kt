package com.examplepokedex.igormattos.tvshowapp.services.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.examplepokedex.igormattos.tvshowapp.services.constants.Constants

@Entity(tableName = Constants.TABLE.NAME)
data class MovieDB (
    @PrimaryKey
    val id: Int,
    val poster_path: String,
    val overview: String,
    val title: String,
    val backdrop_path: String
)