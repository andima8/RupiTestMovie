package com.kotlin.andi.mymovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.kotlin.andi.mymovies.BuildConfig
import com.kotlin.andi.mymovies.R
import com.kotlin.andi.mymovies.databinding.ActivityDetailBinding
import com.kotlin.andi.mymovies.domain.model.Movie

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var dataMovie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataMovie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE) ?: dataMovie
        showDetail(dataMovie)
    }

    private fun showDetail(data: Movie) {
        with(binding) {
            Glide.with(applicationContext)
                .load(BuildConfig.BASE_IMG_URL + data.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(detailMovieImg)

            detailMovieLanguage.text = data.originalLanguage
            tvRating.text = (data.voteAverage?.div(2)).toString()
            detailMovieRating.rating = data.voteAverage?.div(2) ?: 0f
            detailMovieDesc.text = data.overview

            val toolbar = findViewById<Toolbar>(R.id.toolbar)
            toolbar.setTitleTextColor(R.style.Collapse_Title)
            setSupportActionBar(toolbar)

            val collapsingToolbar =
                findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
            collapsingToolbar.apply {
                title = data.title
                setExpandedTitleTextAppearance(R.style.Collapse_Title)
            }
        }
    }
}
