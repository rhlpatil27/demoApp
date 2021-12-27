package com.rahul.demoapp

import com.rahul.demoapp.network.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllMovies() = retrofitService.getAllMovies()
    suspend fun getUpcomingMovies() = retrofitService.getUpcomingMovies()

}