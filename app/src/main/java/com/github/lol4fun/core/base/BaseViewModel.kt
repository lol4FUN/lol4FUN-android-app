package com.github.lol4fun.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.lol4fun.util.CoroutineContextProvider
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

open class BaseViewModel(
    private var coroutineContext: CoroutineContextProvider = CoroutineContextProvider()
) : ViewModel(), KoinComponent {

    protected fun launchDataLoad(
        spinner: MutableLiveData<Boolean>,
        block: suspend () -> Unit
    ) {
        spinner.value = true
        viewModelScope.launch(coroutineContext.IO) { block() }
        spinner.value = false
    }
}