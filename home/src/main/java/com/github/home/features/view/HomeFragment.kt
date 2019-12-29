package com.github.home.features.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.home.R
import com.github.home.features.viewmodel.HomeViewModel
import com.github.lol4fun.extensions.showToast
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: Fragment() {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
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