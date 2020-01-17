package com.github.lol4fun.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.lol4fun.util.CoroutineContextProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

open class BaseViewModel() : ViewModel(), KoinComponent {

    protected var coroutineContext = CoroutineContextProvider()

    protected fun loadDataParallel(list: Collection<Any?>, block: suspend (Any?) -> Unit) =
        viewModelScope.launch(coroutineContext.IO) {
            list.map { async { block(it) } }
                .awaitAll()
        }
}