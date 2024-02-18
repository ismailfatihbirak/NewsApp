package com.example.newsapp.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.model.Article
import com.example.newsapp.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(var nrepo : NewsRepository) : ViewModel() {
    var latestNewsList = MutableLiveData<List<Article>>()
    var categoryNewsList = MutableLiveData<List<Article>>()
    val category = MutableLiveData("health")

    init {
        homePageLatestNews()
        homePageCategoryNews()
    }
    fun homePageLatestNews() {
        CoroutineScope(Dispatchers.Main).launch {
            latestNewsList.value = nrepo.homePageLatestNews()
        }
    }
    fun homePageCategoryNews() {
        CoroutineScope(Dispatchers.Main).launch {
            categoryNewsList.value = nrepo.homePageCategoryNews(category.value!!)
        }
    }



}