package com.example.newsapp.core.vm

import kotlinx.coroutines.CoroutineScope

expect abstract class KmpViewModel constructor() {

    protected val scope: CoroutineScope
}
