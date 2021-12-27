package com.rahul.demoapp.network

import com.rahul.demoapp.model.MovieListModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("3/movie/top_rated?api_key=5b35a9e774fc67102c1be49c428d3389&language=en-US&page=1")
    suspend fun getAllMovies() : Response<MovieListModel>


    @GET("3/movie/upcoming?api_key=5b35a9e774fc67102c1be49c428d3389&language=en-US&page=1")
    suspend fun getUpcomingMovies() : Response<MovieListModel>

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}