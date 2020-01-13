package com.github.profile.feature.view

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.lol4fun.core.model.Vi
import com.github.lol4fun.util.ConstantsUtil.Api.BASE_URL_PROFILE_ICON
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_COLOR_PREFERENCE_DARK
import com.github.lol4fun.util.GlideApp
import com.github.profile.R
import com.github.profile.di.ProfileDependencyInjection
import com.github.profile.feature.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
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
            vgProfileContent.visibility = View.VISIBLE
            pbProfileLoading.visibility = View.GONE

            it?.let {
                GlideApp
                    .with(this)
                    .load("$BASE_URL_PROFILE_ICON${it.profileIconId}.png")
                    .into(civProfileSummonerIcon)

                etProfileSummonerName.text = Editable.Factory.getInstance().newEditable(it.summonerName)
                etProfileName.text = Editable.Factory.getInstance().newEditable(it.name)
                tilProfileEmail.editText?.text = Editable.Factory.getInstance().newEditable(it.email)
                swProfileSystemColorPreference.isChecked = it.colorPreference == FIELD_USER_COLOR_PREFERENCE_DARK
            }
        })
    }
}