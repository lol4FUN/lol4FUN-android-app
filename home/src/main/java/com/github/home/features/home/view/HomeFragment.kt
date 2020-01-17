package com.github.home.features.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.home.R
import com.github.home.adapter.HomeAdapter
import com.github.home.di.HomeDependencyInjection
import com.github.home.features.home.viewmodel.HomeViewModel
import com.github.lol4fun.core.model.CurrentGameInfo
import com.github.lol4fun.extensions.showToast
import com.github.lol4fun.util.ConstantsUtil.Api.BASE_URL_SQUARE_ASSET
import com.github.lol4fun.util.GlideApp
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: HomeAdapter
    private lateinit var currentGame: CurrentGameInfo
    private var encryptedSummonerId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        HomeDependencyInjection.injectModules()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSummoner()
        setupObservables()
        setupListeners()
        setupRecyclerView()
    }

    private fun setupObservables() {
        viewModel.spinner.observe(viewLifecycleOwner, Observer { spinner ->
            spinner?.let { srlHome.isRefreshing = it }
        })

        viewModel.alertMessage.observe(viewLifecycleOwner, Observer { message ->
            message?.let { activity?.showToast(it) }
        })

        viewModel.summoner.observe(viewLifecycleOwner, Observer { summoner ->
            summoner?.id?.let {
                encryptedSummonerId = it
                viewModel.fetchHomeData(encryptedSummonerId)
            } ?: run { /* Get summoner name */ }
        })

        viewModel.history.observe(viewLifecycleOwner, Observer { list ->
            adapter.submitList(list)
        })

        viewModel.currentGame.observe(viewLifecycleOwner, Observer { current ->
            current?.let { setupCurrentGame(it) }
        })

        viewModel.notInCurrentGame.observe(viewLifecycleOwner, Observer { notCurrentGame ->
            notCurrentGame?.let { showCurrentInfo(it) }
        })
    }

    private fun setupListeners() {
        srlHome.setOnRefreshListener {
            if (encryptedSummonerId.isNotBlank()) {
                viewModel.fetchHomeData(encryptedSummonerId)
            }
        }
        btCurrentGame.setOnClickListener { /* Go To next fragment sending currentGame */ }
    }

    private fun setupRecyclerView() {
        adapter = HomeAdapter(context)
        adapter.itemClicked = { activity?.showToast(it.gameId.toString()) }
        rvHistoryMatches.layoutManager = LinearLayoutManager(context)
        rvHistoryMatches.adapter = adapter
    }

    private fun setupCurrentGame(currentGame: CurrentGameInfo) {
        currentGame.also { currentGameInfo ->
            this.currentGame = currentGameInfo
            currentGameInfo.participants.find { it.summonerName == "" }?.champion?.let { champion ->
                GlideApp
                    .with(this)
                    .load("${BASE_URL_SQUARE_ASSET}${champion.image?.full}")
                    .into(ivHomeChampion)
            }
        }
    }

    private fun showCurrentInfo(notInCurrentGame: Boolean) {
        if (notInCurrentGame) {
            clCurrentGame.visibility = View.GONE
            tvNotCurrentGame.visibility = View.VISIBLE
        } else {
            clCurrentGame.visibility = View.VISIBLE
            tvNotCurrentGame.visibility = View.GONE
        }
    }
}