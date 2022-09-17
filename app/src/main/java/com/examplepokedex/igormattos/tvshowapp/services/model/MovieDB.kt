package com.examplepokedex.igormattos.tvshowapp.services.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.examplepokedex.igormattos.tvshowapp.services.constants.Constants
import com.google.gson.annotations.SerializedName

@Entity(tableName = Constants.TABLE.NAME)
data class MovieDB(
    @PrimaryKey
    val id: Int,
    val poster_path: String,
    val vote_average: Float,
    val title: String,
    val backdrop_path: String
)