package com.examplepokedex.igormattos.tvshowapp.view

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.examplepokedex.igormattos.tvshowapp.R
import com.examplepokedex.igormattos.tvshowapp.databinding.HomeFragmentBinding
import com.examplepokedex.igormattos.tvshowapp.services.constants.Constants
import com.examplepokedex.igormattos.tvshowapp.services.model.MoviesResult
import com.examplepokedex.igormattos.tvshowapp.view.adapter.movieadapter.MovieAdapter
import com.examplepokedex.igormattos.tvshowapp.viewmodel.MainViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MovieAdapter

    private lateinit var searchView: SearchView

    private var movieFilter = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = HomeFragmentBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        movieFilter = requireArguments().getInt(Constants.BUNDLE.MOVIEFILTER, 0)

        initSearchBar()
        observe()

        return binding.root
    }


    override fun onResume() {
        super.onResume()
        viewModel.listMovie(movieFilter)
    }

    private fun observe() {
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            binding.recyclerView.layoutManager =
                GridLayoutManager(activity, 3, LinearLayoutManager.VERTICAL, false)
            adapter = MovieAdapter {
                openOverView(it)
            }
            binding.recyclerView.adapter = adapter
            adapter.setMovieList(it.moviesResults)
        })
        viewModel.nameTitle.observe(viewLifecycleOwner, Observer {
            binding.homeToolbar.title = it
        })
    }

    private fun initSearchBar(){
        with(binding.homeToolbar){

            this.inflateMenu(R.menu.search_menu)

            //Recupero o item do menu como searchView para dar acesso ao banco query
            val searchItem = menu.findItem(R.id.menu_search)
            searchView = searchItem.actionView as SearchView

            //abra o campo de busca por padrao
            searchView.isIconified = false

            //configura listener de mudança de texto

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    //extrai string de busca
                    val searchString = searchView.query.toString()
                    //busca na api
                    viewModel.searchPostsTitleContains(searchString)
                    //escondde o teclado
                    searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    //execute busca a cada modifição
                    newText?.let {
                        viewModel.searchPostsTitleContains(it)
                    }
                    return true
                }
            })
        }
    }

    private fun openOverView(moviesResult: MoviesResult) {
        val intent = Intent(activity, OverViewActivity::class.java)
        intent.putExtra("ID", moviesResult.id)
        intent.putExtra("TITLE", moviesResult.title)
        intent.putExtra("BACKDROP_PATH", moviesResult.backdrop_path)
        intent.putExtra("DATE", moviesResult.release_date)
        intent.putExtra("POPULARITY", moviesResult.popularity)
        intent.putExtra("OVERVIEW", moviesResult.overview)
        intent.putExtra("VOTE", moviesResult.vote_average)
        startActivity(intent)
    }

}