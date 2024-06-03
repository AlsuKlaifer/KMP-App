package com.example.newsapp.feature.news.data

import com.example.newsapp.feature.news.data.model.response.ArticleResponse

interface NewsService {

    suspend fun getTopHeadlines(): ArticleResponse
    suspend fun getTopHeadlinesWithCategory(category: String): ArticleResponse
    suspend fun getArticleByTitle(title: String) : ArticleResponse
}