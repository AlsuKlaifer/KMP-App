package com.example.newsapp.feature.news.di

import com.example.newsapp.core.utils.CoroutineDispatchers
import com.example.newsapp.feature.news.data.NewsRepositoryImpl
import com.example.newsapp.feature.news.data.NewsService
import com.example.newsapp.feature.news.data.NewsServiceImpl
import com.example.newsapp.feature.news.domain.NewsRepository
import com.example.newsapp.feature.news.domain.usecase.GetArticleByTitleUseCase
import com.example.newsapp.feature.news.domain.usecase.GetArticleByTitleUseCaseImpl
import com.example.newsapp.feature.news.domain.usecase.GetCategoriesUseCase
import com.example.newsapp.feature.news.domain.usecase.GetCategoriesUseCaseImpl
import com.example.newsapp.feature.news.domain.usecase.GetTopHeadlinesUseCase
import com.example.newsapp.feature.news.domain.usecase.GetTopHeadlinesUseCaseImpl
import com.example.newsapp.feature.news.domain.usecase.GetTopHeadlinesWithCategoryUseCase
import com.example.newsapp.feature.news.domain.usecase.GetTopHeadlinesWithCategoryUseCaseImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val newsModule = module {

    factory<NewsService> {
        NewsServiceImpl(get())
    }

    factory<NewsRepository> {
        NewsRepositoryImpl(get())
    }

    factory<GetTopHeadlinesUseCase> {
        GetTopHeadlinesUseCaseImpl(
            ioDispatcher = get(named(CoroutineDispatchers.IO)),
            get()
        )
    }

    factory<GetArticleByTitleUseCase> {
        GetArticleByTitleUseCaseImpl(
            ioDispatcher = get(named(CoroutineDispatchers.IO)),
            get()
        )
    }

    factory<GetCategoriesUseCase> {
        GetCategoriesUseCaseImpl(
            ioDispatcher = get(named(CoroutineDispatchers.IO)),
            get()
        )
    }

    factory<GetTopHeadlinesWithCategoryUseCase> {
        GetTopHeadlinesWithCategoryUseCaseImpl(
            ioDispatcher = get(named(CoroutineDispatchers.IO)),
            get()
        )
    }

}