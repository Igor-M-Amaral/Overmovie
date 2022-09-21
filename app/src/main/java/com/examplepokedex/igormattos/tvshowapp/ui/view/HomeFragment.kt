package com.examplepokedex.igormattos.tvshowapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.examplepokedex.igormattos.tvshowapp.R
import com.examplepokedex.igormattos.tvshowapp.databinding.HomeFragmentBinding
import com.examplepokedex.igormattos.tvshowapp.utils.Constants
import com.examplepokedex.igormattos.tvshowapp.data.model.MovieDB
import com.examplepokedex.igormattos.tvshowapp.utils.listener.MovieListener
import com.examplepokedex.igormattos.tvshowapp.ui.adapter.movieadapter.MovieAdapter
import com.examplepokedex.igormattos.tvshowapp.ui.viewmodel.MovieListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private val viewModel: MovieListViewModel by viewModel()
    private var adapter = MovieAdapter()

    private lateinit var searchView: SearchView
    private val progressBar: ProgressBar by lazy {
        binding.mainProgressbar
    }

    private var movieFilter = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = HomeFragmentBinding.inflate(layoutInflater)

        movieFilter = requireArguments().getInt(Constants.BUNDLE.MOVIEFILTER, 0)

        initSearchBar()
        observe()

        viewModel.progressBar.observe(viewLifecycleOwner, Observer {
            if (it) showProgressBar() else (hideProgressBar())

        })

        val listener = object : MovieListener {
            override fun onDeleteById(movie: MovieDB) {
            }

            override fun onListClick(id: Int) {
                val intent = Intent(activity, OverViewActivity::class.java)
                val bundle = Bundle()
                bundle.putInt("ID", id)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }

        adapter.attachListener(listener)

        return binding.root
    }


    override fun onResume() {
        super.onResume()
        viewModel.listMovie(movieFilter)
    }

    private fun observe() {
        viewModel.movies.observe(viewLifecycleOwner, Observer { listResult ->
            binding.recyclerView.layoutManager =
                GridLayoutManager(activity, 3, LinearLayoutManager.VERTICAL, false)
            binding.recyclerView.adapter = adapter
            adapter.submitList(listResult)
        })

        viewModel.nameTitle.observe(viewLifecycleOwner, Observer {
            binding.homeToolbar.title = it
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
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


    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

}