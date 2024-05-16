package com.example.newsapp.feature.news.domain

import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.feature.news.data.response.Article

interface NewsRepository {

    suspend fun getTopHeadlines() : ResultWrapper<List<Article>>

}