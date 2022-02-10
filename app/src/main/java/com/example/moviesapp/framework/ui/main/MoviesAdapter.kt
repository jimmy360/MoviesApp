package com.example.moviesapp.framework.ui.main

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.databinding.AdapterMovieBinding
import com.example.moviesapp.domain.Movie
import com.example.moviesapp.framework.ui.common.loadUrl

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var list = emptyList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterMovieBinding.inflate(LayoutInflater.from(parent.context), parent,
            false)
        return ViewHolder(binding)
    }

    internal fun setMovies(list: List<Movie>){
        for (result in list) {
            this.list.toMutableList().add(result)
            notifyItemInserted(this.list.size - 1)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[position], listener = {
            //make something with movie object

        })

    override fun getItemCount(): Int = list.size

    class ViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie, listener: (Movie) -> Unit) = with(binding) {
            binding.titleMovie.text = movie.title
            binding.imageMovie.loadUrl(movie.poster_path)
            binding.root.setOnClickListener{ listener(movie) }
        }
    }
}