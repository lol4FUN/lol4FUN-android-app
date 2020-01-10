package com.github.profile.feature.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.profile.R
import com.github.profile.di.ProfileDependencyInjection
import com.github.profile.feature.viewmodel.ProfileViewModel
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ProfileDependencyInjection.injectModules()

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserFirestore()
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.onSuccessGetUserFirestore.observe(viewLifecycleOwner, Observer {
            it?.let {
                //todo fill user data
            }
        })
    }
}