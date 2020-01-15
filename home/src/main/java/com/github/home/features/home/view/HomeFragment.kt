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
import com.github.home.adapter.ItemClickListener
import com.github.home.di.HomeDependencyInjection
import com.github.home.features.home.viewmodel.HomeViewModel
import com.github.lol4fun.core.model.CurrentGameInfo
import com.github.lol4fun.core.model.Match
import com.github.lol4fun.extensions.showToast
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: HomeAdapter

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

        viewModel.fetchHomeData()
        setupObservables()
        setupListeners()
        setupRecyclerView()
    }

    private fun setupObservables() {
        viewModel.spinner.observe(viewLifecycleOwner, Observer { spinner ->
            spinner?.let {
                srlHome.isRefreshing = it
            }
        })

        viewModel.alertMessage.observe(viewLifecycleOwner, Observer { message ->
            message?.let {
                activity?.showToast(it)
            }
        })

        viewModel.history.observe(viewLifecycleOwner, Observer { list ->
            adapter.submitList(list)
        })

        viewModel.currentGame.observe(viewLifecycleOwner, Observer { current ->
            current?.let {
                setupCurrentGame(it)
            }
        })

        viewModel.notInCurrentGame.observe(viewLifecycleOwner, Observer { notCurrentGame ->
            notCurrentGame?.let {
                //Update data
            }
        })
    }

    private fun setupListeners() {
        srlHome.setOnRefreshListener {
            viewModel.fetchHomeData()
        }
    }

    private fun setupRecyclerView() {
        adapter = HomeAdapter(context)
        adapter.setOnClickListener(object : ItemClickListener<Match> {
            override fun onItemClicked(item: Match) {
                activity?.showToast(item.gameId.toString())
            }
        })

        rvHistoryMatches.layoutManager = LinearLayoutManager(context)
        rvHistoryMatches.adapter = adapter
    }

    private fun setupCurrentGame(currentGame: CurrentGameInfo) {
        //TODO()
    }
}