package com.github.champions.feature.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.champions.R
import com.github.champions.adapter.ChampionsAdapter
import com.github.champions.feature.viewmodel.ChampionsViewModel
import com.github.lol4fun.extensions.showSnackBar
import kotlinx.android.synthetic.main.fragment_champions.*

class ChampionsFragment: Fragment() {

    private lateinit var viewModel: ChampionsViewModel

    private lateinit var championsAdapter: ChampionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_champions, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ChampionsViewModel::class.java)

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
        val layoutManager = LinearLayoutManager(context)
        rvChampions.layoutManager = layoutManager
        championsAdapter = ChampionsAdapter(context, championsList)
        rvChampions.adapter = championsAdapter
    }
}