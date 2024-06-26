package com.example.newsapp.feature.news.domain.usecase

import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.feature.news.data.model.response.Article

interface GetArticleByTitleUseCase {
    suspend operator fun invoke(title: String): ResultWrapper<Article>
}