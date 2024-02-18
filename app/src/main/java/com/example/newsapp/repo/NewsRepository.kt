package com.example.newsapp.repo

import com.example.newsapp.datasource.NewsDataSource
import com.example.newsapp.model.Article

class NewsRepository(var nds:NewsDataSource) {
    suspend fun homePageLatestNews() : List<Article> = nds.homePageLatestNews()
    suspend fun homePageCategoryNews(category:String) : List<Article> = nds.homePageCategoryNews(category)

}