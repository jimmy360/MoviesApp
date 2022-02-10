package com.example.moviesapp.framework.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.framework.ui.common.PaginationScrollListener
import com.example.moviesapp.framework.ui.common.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewmodel : MovieViewModel
    var isLoading : Boolean = false
    private val adapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val gridLayout = GridLayoutManager(applicationContext,2)

        binding.included.recycler.layoutManager = gridLayout
        binding.included.recycler.adapter = adapter

        viewmodel = ViewModelProvider(this)[MovieViewModel::class.java]

        binding.included.recycler.addOnScrollListener(object : PaginationScrollListener(gridLayout) {
            override fun loadMovies() {
                isLoading = true
                viewmodel.currentPage += 1
                //load more movies
                LoadMovies()
            }

            override fun isLastPage(): Boolean {
                return isLastPage()
            }

            override fun isLoading(): Boolean {
                return isLoading
            }
        })

        LoadMovies()
    }

    fun LoadMovies(){

        viewmodel.movies.observe(this, Observer {

            when(it.status) {

                Resource.Status.SUCCESS -> {
                    if(!it.data.isNullOrEmpty()) adapter.setMovies(it.data)
                }

                Resource.Status.ERROR -> Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {

                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}