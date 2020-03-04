package com.example.moviename.movies.movies

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.moviename.BuildConfig
import com.example.moviename.R
import com.example.moviename.databinding.ActivityMainBinding
import com.example.moviename.movies.Constants.MOVIE_DESC
import com.example.moviename.movies.Constants.MOVIE_NAME
import com.example.moviename.movies.Constants.ORIGINAL_LANGUAGE
import com.example.moviename.movies.Constants.POSTER_PATH
import com.example.moviename.movies.Constants.RELEASE_DATE
import com.example.moviename.movies.Constants.VOTE_AVERAGE
import com.example.moviename.movies.movieDetails.MovieDetailsActivity

/**
 * This activity will shows all the movies with picture,name,vote count.
 * and also we can filter the movies list locally based on search content.
 */
class MainActivity : AppCompatActivity(), MovieClick {

    lateinit var mMoviesModel: MoviesModel;
    lateinit var mActivityMainBinding: ActivityMainBinding
    lateinit var moviesAdapter: MoviesAdapter
    val itemsList = mutableListOf<MoviesResults>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        initializeViewModel()
        subscribeMoviesData()
        subscribeCrossClick()
    }

    /**
     * this method is used to initialize the view
     */
    private fun initializeView() {
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }

    /**
     * this method is used to initialize for viewModel
     */
    private fun initializeViewModel() {
        mMoviesModel = ViewModelProviders.of(this).get(MoviesModel::class.java)
        mActivityMainBinding.viewModel = mMoviesModel
        moviesAdapter = MoviesAdapter(itemsList, this)
        mActivityMainBinding.rvMovies.adapter = moviesAdapter
        mActivityMainBinding.progressBar.visibility = View.VISIBLE
        mMoviesModel.callGetMoviesApi(BuildConfig.BASEURL)
    }

    /**
     * subscribe for movies data from server
     */
    private fun subscribeMoviesData() {
        mMoviesModel.getMoviesData().observe(this, Observer {
            mActivityMainBinding.progressBar.visibility = View.GONE
            if (it != null) {
                itemsList.clear()
                itemsList.addAll(it)
                moviesAdapter.notifyDataSetChanged()
            }
        })
    }

    /**
     * subscribe for cross icon clicked.
     */
    private fun subscribeCrossClick() {
        mMoviesModel.crossClicked().observe(this, Observer {
            mActivityMainBinding.etMoviesSearch.setText("")
        })
    }

    override fun onClick(pos: Int) {
        startMovieDetailActivity(position = pos)
    }

    /**
     * start the movie detail activity
     */
    private fun startMovieDetailActivity(position: Int) {
        val listItem = itemsList.get(position)
        val intent = Intent(this, MovieDetailsActivity::class.java)
        val bundle = Bundle()
        bundle.putString(POSTER_PATH, listItem.poster_path)
        bundle.putString(MOVIE_NAME, listItem.title)
        bundle.putString(RELEASE_DATE, listItem.release_date)
        bundle.putString(ORIGINAL_LANGUAGE, listItem.original_language)
        bundle.putString(VOTE_AVERAGE, listItem.vote_average)
        bundle.putString(MOVIE_DESC, listItem.overview)
        intent.putExtras(bundle)
        startActivity(intent)

    }

}
