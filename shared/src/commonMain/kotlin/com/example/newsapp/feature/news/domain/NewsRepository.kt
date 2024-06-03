package com.example.newsapp.feature.news.domain

import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.feature.news.data.model.Category
import com.example.newsapp.feature.news.data.model.response.Article

interface NewsRepository {

    suspend fun getTopHeadlines(): ResultWrapper<List<Article>>
    suspend fun getTopHeadlinesWithCategory(category : String): ResultWrapper<List<Article>>

    suspend fun getArticleByTitle(title: String): ResultWrapper<Article>

    suspend fun getCategories() : ResultWrapper<List<Category>>

}