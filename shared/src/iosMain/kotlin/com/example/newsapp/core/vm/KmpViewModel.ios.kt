package com.example.newsapp.core.vm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

actual abstract class KmpViewModel actual constructor() {
    protected actual val scope: CoroutineScope
        get() = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    fun onCleared() {
        scope.cancel()
    }
}