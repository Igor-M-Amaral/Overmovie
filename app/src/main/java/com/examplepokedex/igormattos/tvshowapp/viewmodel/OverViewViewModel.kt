package com.examplepokedex.igormattos.tvshowapp.viewmodel

import android.app.Application
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.examplepokedex.igormattos.tvshowapp.services.ApiListener
import com.examplepokedex.igormattos.tvshowapp.services.constants.Constants
import com.examplepokedex.igormattos.tvshowapp.services.model.CastModel
import com.examplepokedex.igormattos.tvshowapp.services.model.MoviesModel
import com.examplepokedex.igormattos.tvshowapp.services.repository.MovieRepository

class OverViewViewModel(application: Application) : AndroidViewModel(application) {

    val context = application.applicationContext
    val repository = MovieRepository()

    private val _cast = MutableLiveData<CastModel>()
    val cast: LiveData<CastModel> = _cast

    private var _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private var _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private var _popularity = MutableLiveData<String>()
    val popularity: LiveData<String> = _popularity

    private var _overview = MutableLiveData<String>()
    val overview: LiveData<String> = _overview

    private var _vote = MutableLiveData<String>()
    val vote: LiveData<String> = _vote

    fun setBundle(bundle: Bundle, imgMovieLargePoster: ImageView) {
        Glide.with(context)
            .load(Constants.URL.IMAGE_BASE + bundle.getString("BACKDROP_PATH"))
            .into(imgMovieLargePoster)

        _title.value = bundle.getString("TITLE")
        _date.value = bundle.getString("DATE")
        _popularity.value = bundle.getDouble("POPULARITY").toString()
        _overview.value = bundle.getString("OVERVIEW")
        _vote.value = bundle.getFloat("VOTE").toString()
    }

    fun getCastList(id: Int) {
        repository.getCastList(id, object : ApiListener<CastModel> {
            override fun onSuccess(result: CastModel) {
                _cast.value = result
            }

            override fun onFailure(message: String) {
                TODO("Not yet implemented")
            }

        })
    }

}