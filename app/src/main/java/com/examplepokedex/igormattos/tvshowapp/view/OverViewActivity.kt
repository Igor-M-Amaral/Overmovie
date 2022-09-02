package com.examplepokedex.igormattos.tvshowapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.examplepokedex.igormattos.tvshowapp.databinding.ActivityOverViewBinding
import com.examplepokedex.igormattos.tvshowapp.viewmodel.OverViewViewModel

class OverViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOverViewBinding
    private lateinit var viewModel: OverViewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOverViewBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[OverViewViewModel::class.java]

        intent.extras?.let {
            viewModel.setPoster(it, binding.imgMovieLargePoster)
            viewModel.setText(it)
        }

            observer()

            setContentView(binding.root)

    }

    private fun observer() {
        viewModel.title.observe(this, Observer{ title->
            binding.textTitle.text = title
        })
        viewModel.date.observe(this, Observer{ date->
            binding.textReleaseDate.text = date
        })
        viewModel.popularity.observe(this, Observer{ popularity->
            binding.textPopularity.text = popularity
        })
        viewModel.overview.observe(this, Observer{ overview->
            binding.textOverview.text = overview
        })
    }
}
