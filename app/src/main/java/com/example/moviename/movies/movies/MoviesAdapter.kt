package com.example.moviename.movies.movies

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviename.R
import com.example.moviename.movies.Constants.POSTER_FRONT

/**
 * adapter class for the movie movies list.
 */
public class MoviesAdapter(var items: MutableList<MoviesResults>, var movieCallback: MovieClick) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    var itemsList: MutableList<MoviesResults>
    lateinit var mMovieCallback: MovieClick
    lateinit var mContext: Context

    init {
        itemsList = items;
        mMovieCallback = movieCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        mContext = parent.context
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MoviesViewHolder(v)
    }


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        var moviewResults = itemsList.get(position)

        val imageUrl = POSTER_FRONT + moviewResults.poster_path
        if (moviewResults.poster_path != null)
            Glide.with(mContext)
                .load(imageUrl)
                .into(holder.movieLogo)
        holder.moviewName.setText(moviewResults.title)
        holder.voteCount.setText(mContext.resources.getString(R.string.voteCount) + "" + moviewResults.vote_count)
        holder.clItemMovie.setOnClickListener({

            movieCallback.onClick(position)

        })
    }

    override fun getItemCount(): Int {

        return if (itemsList != null) itemsList.size else 0;
    }

    /**
     * View holder class for the movies in the we are finding the view ids which we are showing.
     */
    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var movieLogo: AppCompatImageView
        lateinit var moviewName: AppCompatTextView
        lateinit var voteCount: AppCompatTextView
        lateinit var clItemMovie: ConstraintLayout

        init {
            Log.d("exe","MoviesViewHolder")
            movieLogo = itemView.findViewById(R.id.ivMovieLogo)
            moviewName = itemView.findViewById(R.id.tvMovieName)
            voteCount = itemView.findViewById(R.id.tvVoteCount)
            clItemMovie = itemView.findViewById(R.id.clItemMovie)

        }
    }

}