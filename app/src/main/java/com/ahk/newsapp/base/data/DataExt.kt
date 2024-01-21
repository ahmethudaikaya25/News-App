package com.ahk.newsapp.base.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

suspend fun <T> runOnDispatcher(dispatcher: CoroutineDispatcher, block: suspend () -> T) =
    withContext(dispatcher) {
        async { block() }
    }.await()
