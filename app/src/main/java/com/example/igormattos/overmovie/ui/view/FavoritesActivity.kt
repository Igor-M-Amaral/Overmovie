package com.example.igormattos.overmovie.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toolbar
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.igormattos.overmovie.R
import com.example.igormattos.overmovie.databinding.ActivityFavoritesBinding
import com.example.igormattos.overmovie.data.model.MovieDB
import com.example.igormattos.overmovie.utils.listener.MovieListener
import com.example.igormattos.overmovie.ui.adapter.favoriteadapter.FavoriteAdapter
import com.example.igormattos.overmovie.ui.viewmodel.FavoritesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModel()
    private var adapter = FavoriteAdapter()
    private lateinit var searchView: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityFavoritesBinding.inflate(layoutInflater)

        binding.homeToolbar.title = "Favorites"


        val listener = object : MovieListener {
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

        binding.homeToolbar.setOnClickListener(this)

        initSearchBar()
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

        viewModel.search.observe(this, Observer {
            binding.recyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.recyclerView.adapter = adapter

            adapter.submitList(it)
        })
    }

    private fun initSearchBar() {
        with(binding.homeToolbar) {
            this.inflateMenu(R.menu.search_menu)

            val searchItem = menu.findItem(R.id.menu_search)
            searchView = searchItem.actionView as SearchView

            searchView.isIconified = false

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    val searchString = searchView.query.toString()
                    viewModel.searchPostsTitleContains(searchString)
                    searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {

                        viewModel.searchPostsTitleContains(it)
                    }
                    return true
                }
            })
        }
    }

    override fun onClick(v: View) {
        finish()
    }

}