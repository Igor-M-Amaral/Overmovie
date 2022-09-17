package com.examplepokedex.igormattos.tvshowapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.examplepokedex.igormattos.tvshowapp.databinding.ActivityFavoritesBinding
import com.examplepokedex.igormattos.tvshowapp.services.model.MovieDB
import com.examplepokedex.igormattos.tvshowapp.services.repository.listener.MovieListener
import com.examplepokedex.igormattos.tvshowapp.view.adapter.favoriteadapter.FavoriteAdapter
import com.examplepokedex.igormattos.tvshowapp.viewmodel.FavoritesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModel()
    private var adapter = FavoriteAdapter()

    private val progressBar: ProgressBar by lazy {
        binding.mainProgressbar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityFavoritesBinding.inflate(layoutInflater)

        binding.homeToolbar.title = "Favorites"

        viewModel.progressBar.observe(this, Observer{
            if(it) showProgressBar() else (hideProgressBar())

        })


        val listener = object : MovieListener{
            override fun onDeleteById(movie: MovieDB) {
                viewModel.deleteFavorite(movie)
                viewModel.listFavorites()
            }

            override fun onListClick(id: Int) {
                val intent = Intent(applicationContext, OverViewActivity::class.java)
                val bundle = Bundle()
                bundle.putInt("ID", id)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }

        adapter.attachListener(listener)



        observer()

        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        viewModel.listFavorites()
    }

    private fun observer() {

        viewModel.movies.observe(this, Observer {
            binding.recyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.recyclerView.adapter = adapter
            adapter.submitList(it)
        })
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

}