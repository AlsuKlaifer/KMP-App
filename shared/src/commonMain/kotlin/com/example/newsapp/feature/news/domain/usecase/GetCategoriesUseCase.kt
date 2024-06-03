package com.example.newsapp.feature.news.domain.usecase

import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.feature.news.data.model.Category

interface GetCategoriesUseCase {
    suspend operator fun invoke(): ResultWrapper<List<Category>>
}