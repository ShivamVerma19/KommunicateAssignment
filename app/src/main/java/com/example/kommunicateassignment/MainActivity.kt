package com.example.kommunicateassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import com.example.kommunicateassignment.Adapters.PopularMovieAdapter
import com.example.kommunicateassignment.Adapters.TopRatedMovieAdapter
import com.example.kommunicateassignment.Interface.Api2Interface
import com.example.kommunicateassignment.Interface.ApiInterface
import com.example.kommunicateassignment.Model.ResponseTopRated
import com.example.kommunicateassignment.Model.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var lv1 : ListView
    private lateinit var tv1 : TextView
    private lateinit var lv2 : ListView
    private lateinit var tv2 : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lv1 = findViewById(R.id.lv1)
        tv1 = findViewById(R.id.tv1)

        lv2 = findViewById(R.id.lv2)
        tv2 = findViewById(R.id.tv2)

        fetchResultsForTopRatedMovies()
        fetchResultsForPopularMovies()
    }

    private fun fetchResultsForPopularMovies() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/3/")
            .build()
            .create(Api2Interface::class.java)

        val retrofitData = retrofitBuilder.getData2()

        retrofitData.enqueue(object : Callback<ResponseTopRated>  {
            override fun onResponse(
                call: Call<ResponseTopRated>,
                response: Response<ResponseTopRated>
            ) {
                val movieResponse = response.body()

                if (movieResponse != null) {
                    // Access data using movieResponse.results
                    setListView2(movieResponse.results)
                } else {
                    Log.d("xx" , "Null")
                }
            }

            override fun onFailure(call: Call<ResponseTopRated>, t: Throwable) {
                Log.d("yy" , t.toString())
            }

        })
    }

    private fun setListView2(results: List<Result>) {
        val PopularMovieAdapter = TopRatedMovieAdapter(this,results)
        lv2.adapter = PopularMovieAdapter
    }

    private fun fetchResultsForTopRatedMovies() {

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/3/")
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<ResponseTopRated>  {
            override fun onResponse(
                call: Call<ResponseTopRated>,
                response: Response<ResponseTopRated>
            ) {
                val movieResponse = response.body()

                if (movieResponse != null) {
                    // Access data using movieResponse.results
                    setListView(movieResponse.results)
                } else {
                    Log.d("xx" , "Null")
                }
            }

            override fun onFailure(call: Call<ResponseTopRated>, t: Throwable) {
                Log.d("yy" , t.toString())
            }

        })
    }

    private fun setListView(subList: List<Result>) {
        val TopRatedMovieAdapter = TopRatedMovieAdapter(this,subList)
        lv1.adapter = TopRatedMovieAdapter
    }
}