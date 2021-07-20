package com.kotlin.andi.mymovies.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.andi.mymovies.databinding.ActivityMainBinding
import com.kotlin.andi.mymovies.ui.MoviesActivity.Companion.EXTRA_GENRE
import com.kotlin.andi.mymovies.ui.adapter.MainAdapter
import com.kotlin.andi.mymovies.utils.invisible
import com.kotlin.andi.mymovies.utils.visible
import com.kotlin.andi.mymovies.viewmodel.MovieViewModel
import com.kotlin.andi.mymovies.vo.Status
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainAdapter = MainAdapter {
            val movieIntent = Intent(this, MoviesActivity::class.java)
            movieIntent.putExtra(EXTRA_GENRE, it)
            startActivity(movieIntent)
        }
        binding.progressbarGenre.visible()
        movieViewModel.getGenre.observe(this, { genre ->
            when(genre.status) {
                Status.LOADING -> binding.progressbarGenre.visible()
                Status.SUCCESS -> {
                    binding.progressbarGenre.invisible()
                    mainAdapter.submitList(genre.data)
                    mainAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    binding.progressbarGenre.invisible()
                    Toast.makeText(this, "System Error", Toast.LENGTH_SHORT).show()
                }
            }
            with(binding.rvGenre) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = mainAdapter
            }
        })
    }
}
