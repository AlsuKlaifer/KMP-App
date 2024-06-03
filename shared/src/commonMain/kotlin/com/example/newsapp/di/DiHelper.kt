package com.example.newsapp.di

import com.example.newsapp.configuration.Configuration
import com.example.newsapp.configuration.configurationModule
import com.example.newsapp.core.network.networkModule
import com.example.newsapp.core.sqldelight.storageModule
import com.example.newsapp.core.utils.dispatchersModule
import com.example.newsapp.feature.auth.di.authModule
import com.example.newsapp.feature.auth.di.signInScreenModule
import com.example.newsapp.feature.auth.di.signUpScreenModule
import com.example.newsapp.feature.categories.di.categoriesModule
import com.example.newsapp.feature.categories.di.newsCategoryModule
import com.example.newsapp.feature.detail.di.detailsModule
import com.example.newsapp.feature.home.di.homeModule
import com.example.newsapp.feature.news.di.newsModule
import com.example.newsapp.feature.profile.di.profileModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(conf: Configuration, appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(
            configurationModule(conf),
            networkModule,
            storageModule,
            dispatchersModule,
            newsModule,
            authModule,

            homeModule,
            profileModule,
            categoriesModule,
            newsCategoryModule,
            detailsModule,
            signInScreenModule,
            signUpScreenModule
        )
    }
}