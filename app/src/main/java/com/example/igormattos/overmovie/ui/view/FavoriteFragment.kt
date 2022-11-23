package com.example.igormattos.overmovie.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.igormattos.overmovie.R
import com.example.igormattos.overmovie.data.model.MovieDB
import com.example.igormattos.overmovie.databinding.FragmentFavoriteBinding
import com.example.igormattos.overmovie.ui.adapter.favoriteadapter.FavoriteAdapter
import com.example.igormattos.overmovie.ui.viewmodel.FavoritesViewModel
import com.example.igormattos.overmovie.utils.listener.MovieListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoritesViewModel by viewModel()
    private var adapter = FavoriteAdapter()
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        binding = FragmentFavoriteBinding.inflate(layoutInflater)

        val listener = object : MovieListener {
            override fun onDeleteMovie(movie: MovieDB) {
                viewModel.deleteFavorite(movie)
                onResume()
            }

            override fun onListClick(id: Int) {
                val action = FavoriteFragmentDirections.navDetails(id)

                findNavController().navigate(action)
            }
        }
        adapter.attachListener(listener)

        binding.homeToolbar.setOnClickListener(this)

        initSearchBar()
        viewModel.listFavorites()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.listFavorites()

        viewModel.movies.observe(viewLifecycleOwner, Observer {
            binding.recyclerView.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            binding.recyclerView.adapter = adapter
            adapter.submitList(it)
        })

    }

    override fun onClick(v: View) {
        findNavController().navigateUp()
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
}