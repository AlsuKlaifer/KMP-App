package com.example.newsapp.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.SetupRequest
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module


private const val API_KEY_NAME = "apiKey"
private const val API_KEY_VALUE = "a2f44aa629424e398cb9c5f1a475544e"
private const val BASE_URL = "newsapi.org/v2"

val networkModule = module {

    single<HttpEngineFactory> {
        HttpEngineFactory()
    }

    single<Json> {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    single(named("ApiKeyPlugin")) {
        createClientPlugin(name = "ApiKeyPlugin") {
            on(SetupRequest) { builder ->
                builder.url.parameters.apply {
                    append(API_KEY_NAME, API_KEY_VALUE)
                }
            }
        }
    }

    single {
        buildHttpClient(
            engine = get<HttpEngineFactory>().createEngine(),
            json = get(),
            apiKeyPlugin = get(named("ApiKeyPlugin")),
        )
    }
}


private fun buildHttpClient(
    engine: HttpClientEngineFactory<HttpClientEngineConfig>,
    json: Json,
    apiKeyPlugin: ClientPlugin<Unit>,
) = HttpClient(engine) {
    defaultRequest {
        url {
            this.host = BASE_URL
            this.protocol = URLProtocol.HTTPS
        }
    }

    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }

    install(ContentNegotiation) {
        json(json)
    }

    install(apiKeyPlugin)

    install(HttpTimeout) {
        connectTimeoutMillis = 15000
        requestTimeoutMillis = 30000
        socketTimeoutMillis = 30000
    }
}

