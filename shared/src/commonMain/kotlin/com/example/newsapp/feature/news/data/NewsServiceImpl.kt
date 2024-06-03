package com.example.newsapp.feature.news.data

import com.example.newsapp.feature.news.data.model.response.ArticleResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol
import io.ktor.http.path

internal class NewsServiceImpl(
    private val httpClient: HttpClient,
) : NewsService {
    override suspend fun getTopHeadlines(): ArticleResponse = httpClient.get {
        url {
            protocol = URLProtocol.HTTPS
            host = "newsapi.org"

            path("v2/top-headlines")
            parameter("pageSize", 50)
            parameter("country", "us")
        }
    }.body()

    override suspend fun getTopHeadlinesWithCategory(category: String): ArticleResponse =
        httpClient.get {
            url {
                protocol = URLProtocol.HTTPS
                host = "newsapi.org"

                path("v2/top-headlines")
                parameter("pageSize", 50)
                parameter("category", category)
                parameter("country", "us")
            }
        }.body()

    override suspend fun getArticleByTitle(title: String): ArticleResponse = httpClient.get {
        url {
            protocol = URLProtocol.HTTPS
            host = "newsapi.org"

            path("v2/top-headlines")
            parameter("country", "us")
            parameter("q", title)
        }
    }.body()
}