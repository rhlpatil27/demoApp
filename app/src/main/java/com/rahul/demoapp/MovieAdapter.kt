package com.rahul.demoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rahul.demoapp.databinding.AdapterMovieBinding
import com.rahul.demoapp.model.MovieListModel

class MovieAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var movieList = MovieListModel()

    fun setMovies(movies: MovieListModel) {
        this.movieList = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val movie = movieList
        holder.binding.name.text = movie.results[position].title
        Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/original/"+movie.results[position].posterPath)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(holder.binding.imageview)
    }

    override fun getItemCount(): Int {
        return movieList.results.size
    }
}

class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {

}