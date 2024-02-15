package com.example.newsapp.model

data class Main(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)