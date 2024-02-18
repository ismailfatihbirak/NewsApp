package com.example.newsapp.viewmodel

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
class LatestNewsAllViewModel @Inject constructor(var nrepo : NewsRepository) : ViewModel() {
    var latestNewsAllList = MutableLiveData<List<Article>>()

    init {
        LatestNewsAll()
    }
    fun LatestNewsAll() {
        CoroutineScope(Dispatchers.Main).launch {
            latestNewsAllList.value = nrepo.homePageLatestNews()
        }
    }
}