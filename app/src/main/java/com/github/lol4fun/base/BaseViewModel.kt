package com.github.lol4fun.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {

    protected fun launchDataLoad(
        spinner: MutableLiveData<Boolean>,
        block: suspend () -> Unit
    ): Job {
        return viewModelScope.launch {
            spinner.value = true
            block()
            spinner.value = false
        }
    }
}