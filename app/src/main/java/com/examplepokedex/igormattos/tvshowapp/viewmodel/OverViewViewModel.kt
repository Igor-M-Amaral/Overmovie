package com.examplepokedex.igormattos.tvshowapp.viewmodel

import android.app.Application
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.examplepokedex.igormattos.tvshowapp.services.constants.Constants

class OverViewViewModel(application: Application) : AndroidViewModel(application) {

    val context = application.applicationContext

    private var _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private var _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private var _popularity = MutableLiveData<String>()
    val popularity: LiveData<String> = _popularity

    private var _overview = MutableLiveData<String>()
    val overview: LiveData<String> = _overview


    fun setPoster(bundle: Bundle, imgMovieLargePoster: ImageView) {
        Glide.with(context)
            .load(Constants.URL.IMAGE_BASE + bundle.getString("BACKDROP_PATH"))
            .into(imgMovieLargePoster)
    }

    fun setText(
        bundle: Bundle,
    ) {
        _title.value = bundle.getString("TITLE")
        _date.value = bundle.getString("DATE")
        _popularity.value = bundle.getDouble("POPULARITY").toString()
        _overview.value = bundle.getString("OVERVIEW")

    }

}