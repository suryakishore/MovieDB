package com.example.moviename.movies.movieDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.moviename.R
import com.example.moviename.databinding.ActivityMovieDetailsBinding
import com.example.moviename.movies.Constants
import com.example.moviename.movies.Constants.MOVIE_DESC
import com.example.moviename.movies.Constants.MOVIE_NAME
import com.example.moviename.movies.Constants.ORIGINAL_LANGUAGE
import com.example.moviename.movies.Constants.POSTER_PATH
import com.example.moviename.movies.Constants.RELEASE_DATE
import com.example.moviename.movies.Constants.VOTE_AVERAGE

/**
 * this class is used to show the movie details if i clicked on movie item.
 */
class MovieDetailsActivity : AppCompatActivity() {
    lateinit var mMovieDetailsBinding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        initializeView()

    }

    /**
     * this method is used to initialize the view and set the bundle data to the views.
     */
    private fun initializeView() {
        mMovieDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)

        var bundle: Bundle? = intent.extras

        if (bundle != null) {
            val imageUrl = Constants.POSTER_FRONT + bundle.getString(POSTER_PATH)
            Glide.with(this)
                .load(imageUrl)
                .into(mMovieDetailsBinding.ivMovieLogo)
            mMovieDetailsBinding.tvMovieName.setText(bundle.getString(MOVIE_NAME))
            mMovieDetailsBinding.tvMovieReleaseDate.setText(
                resources.getString(R.string.releaseDate) + bundle.getString(RELEASE_DATE)
            )
            mMovieDetailsBinding.tvMovieOriginalLanguage.setText(
                resources.getString(R.string.language) + bundle.getString(ORIGINAL_LANGUAGE)
            )
            mMovieDetailsBinding.tvMovieVoteAverage.setText(
                resources.getString(R.string.voteAverage) + bundle.getString(VOTE_AVERAGE)
            )
            mMovieDetailsBinding.tvMovieAboutDesc.setText(bundle.getString(MOVIE_DESC))

        }

        mMovieDetailsBinding.ivBackBtn.setOnClickListener({
            finish()
        })
    }


}
