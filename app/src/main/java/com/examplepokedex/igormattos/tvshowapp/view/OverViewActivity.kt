package com.examplepokedex.igormattos.tvshowapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.examplepokedex.igormattos.tvshowapp.databinding.ActivityOverViewBinding
import com.examplepokedex.igormattos.tvshowapp.view.adapter.castadapter.CastAdapter
import com.examplepokedex.igormattos.tvshowapp.viewmodel.OverViewViewModel

class OverViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOverViewBinding
    private lateinit var viewModel: OverViewViewModel
    private val adapter = CastAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityOverViewBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[OverViewViewModel::class.java]



        intent.extras?.let {
            viewModel.setBundle(it, binding.imgMovieLargePoster)
            viewModel.getCastList(it.getInt("ID"))
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
        viewModel.cast.observe(this, Observer{
            binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerView.adapter = adapter
            adapter.setCastList(it.cast)
        })

    }
}
