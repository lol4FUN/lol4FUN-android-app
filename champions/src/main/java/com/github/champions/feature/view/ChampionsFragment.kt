package com.github.champions.feature.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.champions.R
import com.github.champions.feature.viewmodel.ChampionsViewModel
import com.github.lol4fun.extensions.showSnackBar
import kotlinx.android.synthetic.main.fragment_champions.*

class ChampionsFragment: Fragment() {

    private val viewModel: ChampionsViewModel by lazy { ChampionsViewModel() }

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
            it?.let { championsMap ->
                pbChampionsLoading.visibility = View.GONE
            }
        })
    }
}