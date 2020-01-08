package com.github.profile.feature.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.profile.R
import com.github.profile.di.ProfileDependencyInjection

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ProfileDependencyInjection.injectModules()

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
}