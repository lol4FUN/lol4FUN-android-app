package com.github.home.features.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.home.R
import com.github.home.adapter.HomeAdapter
import com.github.home.di.HomeDependencyInjection
import com.github.home.features.home.viewmodel.HomeViewModel
import com.github.lol4fun.core.model.CurrentGameInfo
import com.github.lol4fun.extensions.setImageDownload
import com.github.lol4fun.extensions.showToast
import com.github.lol4fun.util.ConstantsUtil.Api.BASE_URL_SQUARE_ASSET
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: HomeAdapter
    private lateinit var currentGame: CurrentGameInfo

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
            spinner?.let { pbHomeLoading.visibility = if (it) View.VISIBLE else View.GONE }
        })

        viewModel.alertMessage.observe(viewLifecycleOwner, Observer { message ->
            message?.let { activity?.showToast(it) }
        })

        viewModel.summoner.observe(viewLifecycleOwner, Observer { summoner ->
            summoner?.id?.let {
                viewModel.fetchHomeData(it)
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
        btCurrentGame.setOnClickListener { /* Go To next fragment sending currentGame */ }
    }

    private fun setupRecyclerView() {
        adapter = HomeAdapter(context)
        adapter.itemClicked = { activity?.showToast(it.gameId.toString()) }
        rvHistoryMatches.layoutManager = LinearLayoutManager(context)
        rvHistoryMatches.adapter = adapter
    }

    private fun setupCurrentGame(currentGame: CurrentGameInfo) {
        this.currentGame = currentGame
        currentGame.participants.find { it.summonerName == "" }?.champion?.let { champion ->
            val path = "${BASE_URL_SQUARE_ASSET}${champion.image?.full}"
            ivHomeChampion.setImageDownload(this.context, path)
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