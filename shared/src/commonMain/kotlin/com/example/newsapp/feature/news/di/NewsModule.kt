package com.example.newsapp.feature.news.di

import com.example.newsapp.core.utils.CoroutineDispatchers
import com.example.newsapp.feature.news.data.NewsRepositoryImpl
import com.example.newsapp.feature.news.data.NewsService
import com.example.newsapp.feature.news.data.NewsServiceImpl
import com.example.newsapp.feature.news.domain.NewsRepository
import com.example.newsapp.feature.news.domain.usecase.GetTopHeadlinesUseCase
import com.example.newsapp.feature.news.domain.usecase.GetTopHeadlinesUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val newsModule = DI.Module("newsModule") {

    bindProvider<NewsService> {
        NewsServiceImpl(instance())
    }

    bindProvider<NewsRepository> {
        NewsRepositoryImpl(instance())
    }

    bindProvider<GetTopHeadlinesUseCase> {
        GetTopHeadlinesUseCaseImpl(
            ioDispatcher = instance(tag = CoroutineDispatchers.IO),
            instance()
        )
    }

}