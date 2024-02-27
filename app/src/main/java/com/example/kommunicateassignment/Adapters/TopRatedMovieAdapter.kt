package com.example.kommunicateassignment.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kommunicateassignment.Model.Result
import com.example.kommunicateassignment.R
import com.example.kommunicateassignment.TopRatedMovieDetailsActivity


class TopRatedMovieAdapter(val context : Context, val list : List<Result>) : BaseAdapter() {
    override fun getCount(): Int = list.size

    override fun getItem(position: Int) = list[position]

    override fun getItemId(position: Int) = position.toLong()


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.top_rated_item, null)

        val item = list[position]

        val topRatedName : TextView = view.findViewById(R.id.topRatedName)
        val topRatedRating : TextView = view.findViewById(R.id.topRatedRating)
        val topRatedLanguage : TextView = view.findViewById(R.id.topRatedLanguage)
        val topRatedYear : TextView = view.findViewById(R.id.topRatedName)
        val topRatedImg : ImageView = view.findViewById(R.id.topRatedImg)
        val topRatedShareButton : Button = view.findViewById(R.id.topRatedShareButton)

        topRatedName.text = item.original_title
        topRatedRating.text = item.vote_average.toString()
        topRatedLanguage.text = item.original_language
        topRatedYear.text = item.release_date
        Glide.with(parent!!.context)
            .load("https://image.tmdb.org/t/p/w500"+item.poster_path)
            .into(topRatedImg)

        view.setOnClickListener {

            val intent = Intent(context , TopRatedMovieDetailsActivity::class.java)
            intent.putExtra("TopRatedMovieOverView" , item.overview)
            context.startActivity(intent)
        }

        topRatedShareButton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)

            shareIntent.type = "text/plain"

            val movieName = "Movie Name : " + item.original_title
            val movieOverView = "Movie Overview : " + item.overview
            val combinedString = "$movieName\n$movieOverView"

            shareIntent.putExtra(Intent.EXTRA_TEXT, combinedString)

            // Start the chooser dialog to select an app for sharing
            context.startActivity(Intent.createChooser(shareIntent, "Share using"))
        }
        return view
    }
}