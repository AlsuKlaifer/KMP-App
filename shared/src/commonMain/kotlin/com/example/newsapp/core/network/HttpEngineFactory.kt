package com.example.newsapp.core.network

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

expect open class HttpEngineFactory() {

    fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig>
}