package com.example.newsapp.retrofit

import com.example.newsapp.model.Main
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("top-headlines?country=us&apiKey=2f51a55fb7ee4d6690c5b23bb6a1889e")
    suspend fun getNews():Main

    //@GET("top-headlines?country=us&category=business&apiKey=2f51a55fb7ee4d6690c5b23bb6a1889e")
    //suspend fun getCategoryNews():Main

    //https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=2f51a55fb7ee4d6690c5b23bb6a1889e
    @GET("top-headlines")
    suspend fun getCategoryNews(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Main



}