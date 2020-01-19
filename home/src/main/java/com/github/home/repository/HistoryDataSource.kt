package com.github.home.repository

import android.util.Log
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
import kotlin.system.measureTimeMillis

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
        val matches = mutableListOf<Match>()
        var totalTime = 0L
        val parentJob = scope.launch {
            totalTime = measureTimeMillis {
                val summoner = business.getUserFirestore()
                val start = 0
                val end = start + PAGE_SIZE - 1

                summoner?.accountId?.let { id ->
                    business.getHistory(id, start, end) { matchList ->
                        totalGames = matchList.totalGames
                        matchList.matches.map { reference ->
                            var time = 0L
                            val job = launch {
                                time = measureTimeMillis {
                                    business.getDetailMatch(reference.gameId)?.let { item ->
                                        matches.add(item)
                                    }
                                }
                            }
                            job.invokeOnCompletion {
                                Log.d("DEBUG", "Complete this job in $time")
                            }
                        }
                    }
                }
            }
        }
        parentJob.invokeOnCompletion {
            callback.onResult(matches, null, PAGE_SIZE)
            Log.d("DEBUG", "Complete TOTAL job in $totalTime")
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Match>) {
        val start = params.key
        val end = if (start + params.key - 1 <= totalGames) {
            start + params.key - 1
        } else totalGames
        val matches = mutableListOf<Match>()

        val parentJob = scope.launch {
            val summoner = business.getUserFirestore()

            summoner?.accountId?.let { id ->
                business.getHistory(id, start, end) { matchList ->
                    matchList.matches.map { reference ->
                        launch {
                            business.getDetailMatch(reference.gameId)?.let { item ->
                                matches.add(item)
                            }
                        }
                    }
                }
            }
        }
        parentJob.invokeOnCompletion {
            callback.onResult(matches, start + params.key)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Match>) {}

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }
}