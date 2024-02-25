package com.example.newsapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform