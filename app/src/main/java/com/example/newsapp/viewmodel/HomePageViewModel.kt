package com.example.newsapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.model.Article
import com.example.newsapp.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(var nrepo : NewsRepository) : ViewModel() {
    var latestNewsList = MutableLiveData<List<Article>>()

    init {
        homePageLatestNews()

    }
    fun homePageLatestNews() {
        CoroutineScope(Dispatchers.Main).launch {
            latestNewsList.value = nrepo.homePageLatestNews()


        }
    }



}