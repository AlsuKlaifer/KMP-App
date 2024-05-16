package com.example.newsapp.core

import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

class CommonTestFlow<T : Any> internal constructor(
    private val flow: Flow<T>,
) {

    fun subscribe(
        next: (T) -> Unit,
        completion: (Throwable?) -> Unit,
    ): () -> Unit {
        val job = MainScope().launch {
            flow.onCompletion {
                completion(it)
            }.collect {
                next(it)
            }
        }
        return ({ job.cancel() })
    }
}

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
fun <T : Any> Flow<T>.asCommonFlowTest() = CommonTestFlow(this)


@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
fun <T : Any> SharedFlow<T>.asCommonFlow() = CommonFlow(this)

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
fun <T : Any> StateFlow<T>.asCommonStateFlow() = CommonStateFlow(this)

class CommonFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {

    fun watch(block: (T) -> Unit): Closeable = watchFlow(block)
}

class CommonStateFlow<T : Any>(private val origin: StateFlow<T>) : StateFlow<T> by origin {
    fun watch(block: (T) -> Unit): Closeable = watchFlow(block)
}

private fun <T> Flow<T>.watchFlow(block: (T) -> Unit): Closeable {
    val context = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    onEach(block).launchIn(context)
    return object : Closeable {
        override fun close() {
            context.cancel()
        }
    }
}
