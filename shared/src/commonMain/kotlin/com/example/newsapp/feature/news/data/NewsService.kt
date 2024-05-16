package com.example.newsapp.feature.news.data

import com.example.newsapp.feature.news.data.response.ArticleResponse

interface NewsService {

    suspend fun getTopHeadlines(): ArticleResponse
}