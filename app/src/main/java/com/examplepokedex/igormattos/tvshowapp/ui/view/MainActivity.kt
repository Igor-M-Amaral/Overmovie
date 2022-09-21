package com.examplepokedex.igormattos.tvshowapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.examplepokedex.igormattos.tvshowapp.R
import com.examplepokedex.igormattos.tvshowapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        supportActionBar?.hide()

        setContentView(binding.root)


        val navHostFragment =
            (supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment)

        val navController = navHostFragment.navController
        binding.buttonNavigationView.setupWithNavController(navController)

        binding.fab.setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_search){
            return false
        }
        return super.onOptionsItemSelected(item)
    }

}


