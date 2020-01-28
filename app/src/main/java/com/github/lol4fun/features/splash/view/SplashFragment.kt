package com.github.lol4fun.features.splash.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.lol4fun.R
import com.github.lol4fun.features.splash.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SplashFragment : Fragment() {

    private val viewModel: SplashViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservables()
    }

    private fun initObservables() {
        viewModel.goToHome.observe(viewLifecycleOwner, Observer { goToHome ->
            goToHome?.let { findNavController().navigate(R.id.splash_to_home) }
            activity?.finish()
        })

        viewModel.goToNickname.observe(viewLifecycleOwner, Observer { goToNickname ->
            goToNickname?.let { findNavController().navigate(R.id.splash_to_nickname) }
        })
    }
}