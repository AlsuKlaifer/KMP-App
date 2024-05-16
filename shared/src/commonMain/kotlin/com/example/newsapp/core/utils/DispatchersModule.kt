package com.example.newsapp.core.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.kodein.di.DI
import org.kodein.di.bindProvider

//ИНТЕРНАЛ
val dispatchersModule = DI.Module("dispatcherModule") {
    bindProvider<CoroutineDispatcher>(tag = CoroutineDispatchers.MAIN) { Dispatchers.Main }
    bindProvider<CoroutineDispatcher>(tag = CoroutineDispatchers.IO) { Dispatchers.IO }
    bindProvider<CoroutineDispatcher>(tag = CoroutineDispatchers.DEFAULT) { Dispatchers.Default }
    bindProvider<CoroutineDispatcher>(tag = CoroutineDispatchers.UNCONFINED) { Dispatchers.Unconfined }
}

//АПИ
enum class CoroutineDispatchers {
    MAIN, IO, UNCONFINED, DEFAULT,
}
