package com.example.igormattos.overmovie.ui.view

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.igormattos.overmovie.R
import com.example.igormattos.overmovie.utils.Constants
import com.example.igormattos.overmovie.data.paging.LoaderAdapter
import com.example.igormattos.overmovie.databinding.FragmentHomeBinding
import com.example.igormattos.overmovie.ui.adapter.movieadapter.MovieAdapter
import com.example.igormattos.overmovie.ui.viewmodel.MovieListViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MovieListViewModel by viewModel()
    private var adapter = MovieAdapter { movieId ->
        onItemClicked(movieId)
    }

    private lateinit var searchView: SearchView
    private val progressBar: ProgressBar by lazy {
        binding.mainProgressbar
    }

    private var movieFilter = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieFilter = requireArguments().getString(Constants.BUNDLE.MOVIEFILTER, "popular")

        initSearchBar()
        observe()
    }

    override fun onResume() {
        super.onResume()
        viewModel.listMovie(movieFilter)
    }

    private fun observe() {
        viewModel.movies.observe(viewLifecycleOwner, Observer { listResult ->
            lifecycleScope.launch {
                binding.recyclerView.layoutManager =
                    GridLayoutManager(activity, 3, LinearLayoutManager.VERTICAL, false)
                binding.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                    header = LoaderAdapter(),
                    footer = LoaderAdapter()
                )
                listResult.collect {
                    adapter.submitData(it)
                }
            }
        })

        viewModel.search.observe(viewLifecycleOwner, Observer { listResult ->
            lifecycleScope.launch {
                binding.recyclerView.layoutManager =
                    GridLayoutManager(activity, 3, LinearLayoutManager.VERTICAL, false)
                binding.recyclerView.adapter = adapter
                listResult.collect {
                    adapter.submitData(it)
                }
            }
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun onItemClicked(movieId: Int) {
        val action = HomeFragmentDirections.navDetails(movieId)

        findNavController().navigate(action)
    }

    private fun initSearchBar() {
        with(requireActivity().findViewById<Toolbar>(R.id.toolbar)) {
            this.menu.clear()
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