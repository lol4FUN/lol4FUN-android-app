package com.github.home.repository

import androidx.paging.PageKeyedDataSource
import com.github.home.features.home.business.HomeBusiness
import com.github.lol4fun.core.model.Match
import com.github.lol4fun.util.ConstantsUtil.Home.PAGE_SIZE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf
import kotlin.coroutines.CoroutineContext

class HistoryDataSource(
    context: CoroutineContext
) : PageKeyedDataSource<Int, Match>(), KoinComponent {

    private val job = Job()
    private val scope = CoroutineScope(context + job)
    private val business: HomeBusiness by inject { parametersOf(null) }

    private var totalGames = 0

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Match>
    ) {
        val matches = mutableMapOf<Long, Match?>()
        business.setHomeLoading(true)
        val job = scope.launch {
            val summoner = business.getUserFirestore()
            val start = 0
            val end = start + PAGE_SIZE

            summoner?.accountId?.let { id ->
                business.getHistory(id, start, end) { matchList ->
                    totalGames = matchList.totalGames
                    matchList.matches.map { ref ->
                        matches[ref.gameId] = null
                        launch {
                            business.getDetailMatch(ref.gameId)?.let { matches[it.gameId] = it }
                        }
                    }
                }
            }
        }

        job.invokeOnCompletion {
            business.setHomeLoading(false)
            callback.onResult(matches.values.toList(), null, PAGE_SIZE)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Match>) {
        val start = params.key
        val end = if (start + PAGE_SIZE <= totalGames) {
            start + PAGE_SIZE
        } else totalGames
        val matches = mutableMapOf<Long, Match?>()
        business.setHomeLoading(true)
        val job = scope.launch {
            val summoner = business.getUserFirestore()

            summoner?.accountId?.let { id ->
                business.getHistory(id, start, end) { matchList ->
                    matchList.matches.map { ref ->
                        matches[ref.gameId] = null
                        launch { business.getDetailMatch(ref.gameId)?.let { matches[it.gameId] = it } }
                    }
                }
            }
        }

        job.invokeOnCompletion {
            business.setHomeLoading(false)
            callback.onResult(matches.values.toList(), end)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Match>) {}

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }
}