package com.example.newsapp.feature.news.data.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    @SerialName("source")
    val articleSource: ArticleSource?,
    @SerialName("author")
    val author: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("url")
    val url: String?,
    @SerialName("urlToImage")
    val urlToImage: String?,
    @SerialName("publishedAt")
    val publishedAt: String?,
    @SerialName("content")
    val content: String?
)

@Serializable
data class ArticleSource(
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?
)