package com.example.newsapp.di

import com.example.newsapp.configuration.Configuration
import com.example.newsapp.feature.news.data.NewsRepositoryImpl
import com.example.newsapp.feature.news.data.NewsService
import com.example.newsapp.feature.news.data.NewsServiceImpl
import com.example.newsapp.feature.news.domain.NewsRepository
import com.example.newsapp.feature.news.domain.usecase.GetArticleByTitleUseCaseImpl
import com.example.newsapp.feature.news.domain.usecase.GetCategoriesUseCaseImpl
import com.example.newsapp.feature.news.domain.usecase.GetTopHeadlinesUseCaseImpl
import com.example.newsapp.platformModule
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(
    enableNetworkLogs: Boolean = false,
    conf: Configuration,
    appDeclaration: KoinAppDeclaration = {}
) =
    startKoin {
        appDeclaration()
        modules(
            commonModule(enableNetworkLogs = enableNetworkLogs, conf = conf),
        )
    }

// called by iOS etc
fun initKoin(conf: Configuration) = initKoin(enableNetworkLogs = true,conf = conf) {}

fun commonModule(enableNetworkLogs: Boolean, conf: Configuration) =
    getUseCaseModule() + getDataModule(enableNetworkLogs) + platformModule() + configurationModule(conf)
fun configurationModule(conf: Configuration) = module {
    single { conf }
    single { conf.platformConfiguration }
}

fun getDataModule(enableNetworkLogs: Boolean) = module {

    single { createJson() }

    single {
        createHttpClient(
            get(),
            get(),
            enableNetworkLogs = enableNetworkLogs
        )
    }

    single<NewsRepository> {
        NewsRepositoryImpl(
            get()
        )
    }

    single<NewsService> {
        NewsServiceImpl(
            get()
        )
    }
}

fun getUseCaseModule() = module {

    single {
        GetTopHeadlinesUseCaseImpl(Dispatchers.Default, get())
    }

    single {
        GetCategoriesUseCaseImpl(Dispatchers.Default, get())
    }

    single {
        GetArticleByTitleUseCaseImpl(Dispatchers.Default, get())
    }
}


fun createHttpClient(
    httpClientEngine: HttpClientEngine,
    json: Json,
    enableNetworkLogs: Boolean
) =
    HttpClient(httpClientEngine) {

        install(ContentNegotiation) {
            json(json)
        }
        if (enableNetworkLogs) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }
    }

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }