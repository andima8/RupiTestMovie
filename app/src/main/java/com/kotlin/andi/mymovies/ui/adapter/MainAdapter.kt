package com.kotlin.andi.mymovies.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.andi.mymovies.R
import com.kotlin.andi.mymovies.databinding.ListGenreBinding
import com.kotlin.andi.mymovies.domain.model.Genre

class MainAdapter(private val onItemClick: (Genre) -> Unit) : PagedListAdapter<Genre, MainAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Genre>() {
            override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_genre, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie, onItemClick)
        }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListGenreBinding.bind(itemView)
        fun bind(genre: Genre, onItemClick: (Genre) -> Unit) {
            with(binding) {
                tvGenre.text = genre.name
                //ratingMovies.rating = movies.voteAverage?.div(2) ?: 0f
                root.setOnClickListener {
                    onItemClick(genre)
                }
                /*Glide.with(itemView.context)
                    .load(BuildConfig.BASE_IMG_URL + movies.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    ).into(imgMovies)*/
            }
        }
    }
}
