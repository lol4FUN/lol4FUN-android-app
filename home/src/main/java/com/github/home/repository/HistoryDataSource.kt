package com.github.home.repository

import androidx.paging.PageKeyedDataSource
import com.github.home.features.home.business.HomeBusiness
import com.github.lol4fun.core.model.Match
import com.github.lol4fun.core.model.MatchReference
import kotlinx.coroutines.*
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
        scope.launch {
            val summoner = business.getUserFirestore()
            val start = 0
            val end = start + params.requestedLoadSize - 1

            summoner?.accountId?.let { id ->
                business.getHistory(
                    accountId = id,
                    startIndex = start,
                    endIndex = end
                ) { matchList ->
                    totalGames = matchList.totalGames
                    matchReferenceToMatchDetail(matchList.matches) {
                        callback.onResult(it, null, params.requestedLoadSize)
                    }
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Match>) {
        scope.launch {
            val summoner = business.getUserFirestore()
            val start = params.key
            val end = if (start + params.requestedLoadSize - 1 <= totalGames) {
                start + params.requestedLoadSize - 1
            } else totalGames

            summoner?.accountId?.let { id ->
                business.getHistory(
                    accountId = id,
                    startIndex = start,
                    endIndex = end
                ) { matchList ->
                    matchReferenceToMatchDetail(matchList.matches) {
                        callback.onResult(it, start + params.requestedLoadSize)
                    }
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Match>) {

    }

    private fun getDataParallel(list: List<MatchReference>, block: suspend (Long) -> Unit) =
        scope.launch {
            list.map { async { block(it.gameId) } }.awaitAll()
        }

    private fun matchReferenceToMatchDetail(
        matchList: List<MatchReference>,
        block: (List<Match>) -> Unit
    ) {
        val list = mutableListOf<Match>()
        getDataParallel(matchList) { gameId ->
            business.getDetailMatch(gameId) {
                list.add(it)

                if (list.size == matchList.size) {
                    block(list)
                }
            }
        }
    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }
}