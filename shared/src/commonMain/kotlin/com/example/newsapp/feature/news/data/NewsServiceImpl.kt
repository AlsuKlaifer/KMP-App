package com.example.newsapp.feature.news.data

import com.example.newsapp.feature.news.data.model.response.ArticleResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.path

internal class NewsServiceImpl(
    private val client: HttpClient,
) : NewsService {
    override suspend fun getTopHeadlines(): ArticleResponse = client.get {
        url {
            path("top-headlines")
            parameter("country", "us")
        }
    }.body()

    //хз
    override suspend fun getArticleByTitle(title: String): ArticleResponse = client.get {
        url {
            path("top-headlines")
            parameter("country", "us")
            parameter("q", title)
        }
    }.body()
}