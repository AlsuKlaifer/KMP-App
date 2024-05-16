package com.example.newsapp.core.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.SetupRequest
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.kodein.di.singleton


//todo
private const val API_KEY_NAME = ""
private const val API_KEY_VALUE = ""
private const val BASE_URL = ""

val networkModule = DI.Module(name = "networkModule") {

    bind<HttpEngineFactory>() with singleton { HttpEngineFactory() }

    bindSingleton<Json> {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    bindSingleton(tag = "ApiKeyPlugin") {
        createClientPlugin(name = "ApiKeyPlugin") {
            on(SetupRequest) { builder ->
                builder.url.parameters.apply {
                    append(API_KEY_NAME, API_KEY_VALUE)
                }
            }
        }
    }

    bindSingleton {
        buildHttpClient(
            engine = instance<HttpEngineFactory>().createEngine(),
            json = instance(),
            apiKeyPlugin = instance(tag = "ApiKeyPlugin"),
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
        logger = object : Logger {
            override fun log(message: String) {
                Log.v("Logger Ktor =>", message)
            }

        }
        level = LogLevel.ALL
    }

    install(ResponseObserver) {
        onResponse { response ->
            Log.d("HTTP status:", "${response.status.value}")
        }
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

