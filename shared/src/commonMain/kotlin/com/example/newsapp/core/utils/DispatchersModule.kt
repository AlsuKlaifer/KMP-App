package com.example.newsapp.core.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.qualifier.named
import org.koin.dsl.module

//ИНТЕРНАЛ
val dispatchersModule = module {
    factory<CoroutineDispatcher>(named(CoroutineDispatchers.MAIN)) { Dispatchers.Main }
    factory<CoroutineDispatcher>(named(CoroutineDispatchers.IO)) { Dispatchers.IO }
    factory<CoroutineDispatcher>(named(CoroutineDispatchers.DEFAULT)) { Dispatchers.Default }
    factory<CoroutineDispatcher>(named(CoroutineDispatchers.UNCONFINED)) { Dispatchers.Unconfined }
}

//АПИ
enum class CoroutineDispatchers {
    MAIN, IO, UNCONFINED, DEFAULT,
}
