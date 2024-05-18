package com.example.newsapp.feature.news.domain.usecase

import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.feature.news.data.model.Category
import com.example.newsapp.feature.news.data.model.response.Article

interface GetCategoriesUseCase {
    suspend operator fun invoke(title: String): ResultWrapper<List<Category>>
}