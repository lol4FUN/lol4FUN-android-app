package com.github.home.repository

import androidx.paging.PageKeyedDataSource
import com.github.home.features.home.business.HomeBusiness
import com.github.lol4fun.core.model.Match
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf
import kotlin.coroutines.CoroutineContext

class HistoryDataSource(
    context: CoroutineContext
) : PageKeyedDataSource<Long, Match>(), KoinComponent {

    private val job = Job()
    private val scope = CoroutineScope(context + job)
    private val business: HomeBusiness by inject { parametersOf(null) }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Match>
    ) {
        scope.launch {
            //TODO()
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Match>) {
        scope.launch {
            //TODO()
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Match>) {
        scope.launch {
            //TODO()
        }
    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }
}