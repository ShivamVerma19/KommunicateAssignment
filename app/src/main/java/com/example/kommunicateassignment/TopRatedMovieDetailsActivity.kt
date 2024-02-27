package com.example.kommunicateassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TopRatedMovieDetailsActivity : AppCompatActivity() {
    private lateinit var tvOverview : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_rated_movie_details)

        tvOverview = findViewById(R.id.tvOverview)

        val overview = intent.getStringExtra("TopRatedMovieOverView")

        tvOverview.text = overview
    }
}