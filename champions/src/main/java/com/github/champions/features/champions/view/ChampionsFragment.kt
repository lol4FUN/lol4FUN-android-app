package com.github.champions.features.champions.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.github.champions.R
import com.github.champions.adapter.ChampionsAdapter
import com.github.champions.di.ChampionsDependencyInjection
import com.github.champions.features.champions.viewmodel.ChampionsViewModel
import com.github.lol4fun.extensions.showSnackBar
import com.github.lol4fun.util.ConstantsUtil.Champions.NUMBER_OF_COLUMNS_GRID_CHAMPIONS
import kotlinx.android.synthetic.main.fragment_champions.*
import org.koin.android.ext.android.inject

class ChampionsFragment: Fragment() {

    private val viewModel: ChampionsViewModel by inject()

    private lateinit var championsAdapter: ChampionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ChampionsDependencyInjection.injectModules()

        return inflater.inflate(R.layout.fragment_champions, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getChampions()
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.onErrorGetChampionsListLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                pbChampionsLoading.visibility = View.GONE
                this.context?.showSnackBar(pbChampionsLoading, it)
            }
        })

        viewModel.onSuccessGetChampionsListLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { championsList ->
                setupRecyclerView(context, championsList)
                pbChampionsLoading.visibility = View.GONE
            }
        })
    }

    private fun setupRecyclerView(context: Context?, championsList: ArrayList<Any>) {
        val layoutManager = GridLayoutManager(context, NUMBER_OF_COLUMNS_GRID_CHAMPIONS)
        rvChampions.layoutManager = layoutManager
        championsAdapter = ChampionsAdapter(context, championsList)
        rvChampions.adapter = championsAdapter
    }
}