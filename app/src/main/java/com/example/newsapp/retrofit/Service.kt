package com.example.newsapp.retrofit

import com.example.newsapp.model.Main
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("top-headlines?country=us&apiKey=2f51a55fb7ee4d6690c5b23bb6a1889e")
    suspend fun getNews():Main
}