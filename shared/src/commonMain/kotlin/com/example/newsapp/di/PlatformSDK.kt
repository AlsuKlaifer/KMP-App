package com.example.newsapp.di

import com.example.newsapp.core.network.networkModule
import com.example.newsapp.core.utils.dispatchersModule
import com.example.newsapp.feature.news.di.newsModule
import org.kodein.di.DI
import org.kodein.di.DirectDI
import org.kodein.di.LazyDelegate
import org.kodein.di.direct
import org.kodein.di.instance

object PlatformSDK {

    private var _di: DirectDI? = null

    val di: DirectDI
        get() = requireNotNull(_di)

    fun init() {
        _di = DI {
            importAll(
                networkModule,
                dispatchersModule,
                newsModule,
            )
        }.direct
    }

    inline fun <reified T : Any> lazyInstance(tag: Any? = null): LazyDelegate<T> {
        return di.lazy.instance(tag)
    }
}
