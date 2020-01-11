package com.github.home.features.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.home.R
import com.github.home.di.HomeDependencyInjection
import com.github.home.features.home.viewmodel.HomeViewModel
import com.github.lol4fun.core.model.MatchList
import com.github.lol4fun.extensions.showToast
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

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
    }

    private fun setupObservables() {
        viewModel.spinner.observe(viewLifecycleOwner, Observer {
            srlHome.isRefreshing = it
        })

        viewModel.alertMessage.observe(viewLifecycleOwner, Observer {
            activity?.showToast(it)
        })

        viewModel.history.observe(viewLifecycleOwner, Observer {
            setupRecyclerView(it)
        })

        viewModel.detailMatch.observe(viewLifecycleOwner, Observer {

        })
    }

    private fun setupListeners() {
        srlHome.setOnRefreshListener {
            viewModel.fetchHomeData()
        }
    }

    private fun setupRecyclerView(matches: MatchList) {

    }
}