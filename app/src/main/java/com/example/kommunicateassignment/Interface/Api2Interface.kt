package com.example.kommunicateassignment.Interface

import com.example.kommunicateassignment.Model.ResponseTopRated
import retrofit2.Call
import retrofit2.http.GET

interface Api2Interface {

    @GET("movie/popular?api_key=b0d27bfacc7ffbd17af04efe550a0849")
    fun getData2() : Call<ResponseTopRated>
}