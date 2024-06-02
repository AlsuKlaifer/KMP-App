package com.example.newsapp.di

import com.example.newsapp.com.example.newsapp.feature.categories.di.categoriesModule
import com.example.newsapp.com.example.newsapp.feature.detail.di.detailsModule
import com.example.newsapp.com.example.newsapp.feature.home.di.homeModule
import com.example.newsapp.com.example.newsapp.feature.profile.di.profileModule
import com.example.newsapp.core.network.networkModule
import com.example.newsapp.core.utils.dispatchersModule
import com.example.newsapp.feature.auth.di.authModule
import com.example.newsapp.feature.news.di.newsModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            // viewModelModule(),
            networkModule,
            dispatchersModule,
            newsModule,
            authModule,

            homeModule,
            profileModule,
            categoriesModule,
            detailsModule,
        )
    }
}