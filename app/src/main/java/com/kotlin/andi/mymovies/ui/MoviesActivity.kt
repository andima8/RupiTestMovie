package com.kotlin.andi.mymovies.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.andi.mymovies.databinding.ActivityMoviesBinding
import com.kotlin.andi.mymovies.domain.model.Genre
import com.kotlin.andi.mymovies.ui.adapter.MoviesAdapter
import com.kotlin.andi.mymovies.utils.invisible
import com.kotlin.andi.mymovies.utils.visible
import com.kotlin.andi.mymovies.viewmodel.MovieViewModel
import com.kotlin.andi.mymovies.vo.Status
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_GENRE = "extra_genre"
    }

    private lateinit var binding: ActivityMoviesBinding
    private lateinit var dataMovie: Genre
    private val movieViewModel: MovieViewModel by viewModel()
    private lateinit var genre: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataMovie = intent.getParcelableExtra(EXTRA_GENRE) ?: dataMovie
        genre = dataMovie.name.toString()

        val movieAdapter = MoviesAdapter {
            val movieIntent = Intent(this, MoviesActivity::class.java)
            movieIntent.putExtra(EXTRA_GENRE, it)
            startActivity(movieIntent)
        }
        binding.progressbarMovies.visible()
        movieViewModel.getMovie(genre).observe(this, { movie ->
            when(movie.status) {
                Status.LOADING -> binding.progressbarMovies.visible()
                Status.SUCCESS -> {
                    binding.progressbarMovies.invisible()
                    movieAdapter.submitList(movie.data)
                    movieAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    binding.progressbarMovies.invisible()
                    Toast.makeText(this, "System Error", Toast.LENGTH_SHORT).show()
                }
            }
            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        })
    }
}
