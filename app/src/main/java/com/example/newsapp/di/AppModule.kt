package com.example.newsapp.di

import com.example.newsapp.datasource.NewsDataSource
import com.example.newsapp.repo.NewsRepository
import com.example.newsapp.retrofit.ApiUtils
import com.example.newsapp.retrofit.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideNewsDataSource(srcv:Service) : NewsDataSource {
        return NewsDataSource(srcv)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(nds:NewsDataSource) : NewsRepository{
        return NewsRepository(nds)
    }
    @Provides
    @Singleton
    fun provideService2() : Service{
        return ApiUtils.getService()
    }
}