package com.example.newsapp.datasource

import android.util.Log
import com.example.newsapp.model.Article
import com.example.newsapp.retrofit.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsDataSource(var srcv:Service) {
    suspend fun homePageLatestNews() : List<Article> =
        withContext(Dispatchers.IO){
            return@withContext srcv.getNews().articles
        }



}