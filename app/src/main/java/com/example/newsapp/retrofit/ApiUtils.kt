package com.example.newsapp.retrofit

class ApiUtils {
    companion object{
        var BASE_URL : String = "https://newsapi.org/v2/"

        fun getService() :Service {
            return RetrofitClient.getClient(BASE_URL).create(Service::class.java)
        }
    }
}