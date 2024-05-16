package com.example.newsapp.feature.news.data

import com.example.newsapp.feature.news.data.response.ArticleResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.path

internal class NewsServiceImpl(
    private val client: HttpClient
) : NewsService {
    override suspend fun getTopHeadlines(): ArticleResponse = client.get {
        url {
            path("top-headlines")
            parameter("country", "ru")
        }
    }.body()
}