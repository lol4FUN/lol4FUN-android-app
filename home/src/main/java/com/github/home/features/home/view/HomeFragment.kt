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

        viewModel.fetchPlayerHistory()
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.spinner.observe(viewLifecycleOwner, Observer {
            val visibility = if (it) View.VISIBLE else View.GONE
            pbHome.visibility = visibility
        })

        viewModel.alertMessage.observe(viewLifecycleOwner, Observer {
            activity?.showToast(it)
        })
    }
}