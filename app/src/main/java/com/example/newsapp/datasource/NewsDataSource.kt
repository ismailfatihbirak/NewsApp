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
    suspend fun homePageCategoryNews(category :String) : List<Article> =
        withContext(Dispatchers.IO){
            return@withContext srcv.getCategoryNews("us",category,"2f51a55fb7ee4d6690c5b23bb6a1889e").articles
        }
    suspend fun LatestNewsAll() : List<Article> =
        withContext(Dispatchers.IO){
            return@withContext srcv.getNews().articles
        }




}