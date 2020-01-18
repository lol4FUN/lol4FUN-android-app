package com.github.profile.feature.view

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.lol4fun.core.model.Customer
import com.github.lol4fun.extensions.showSnackBar
import com.github.lol4fun.extensions.showToast
import com.github.lol4fun.util.ConstantsUtil.Api.BASE_URL_PROFILE_ICON
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_COLOR_PREFERENCE
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_COLOR_PREFERENCE_DARK
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_COLOR_PREFERENCE_LIGHT
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_NAME
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_SUMMONER_NAME
import com.github.lol4fun.util.GlideApp
import com.github.profile.R
import com.github.profile.di.ProfileDependencyInjection
import com.github.profile.feature.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModel()

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
                fillUserInfo(it)
            }
        })

        btProfileSave.setOnClickListener {
            if (!etProfileName.text.toString().isBlank() && !etProfileSummonerName.text.toString().isBlank()) {
                pbProfileLoading.visibility = View.VISIBLE

                val hashMapProfile = getHashMapProfile()

                viewModel.saveUserInfo(hashMapProfile)
            } else {
                context?.showSnackBar(btProfileSave, R.string.error_invalid_field)
            }
        }

        viewModel.onFailureSaveInfoData.observe(viewLifecycleOwner, Observer {
            it?.let {
                pbProfileLoading.visibility = View.GONE
                context?.showSnackBar(btProfileSave, it)
            }
        })

        viewModel.onSuccessSaveInfoData.observe(viewLifecycleOwner, Observer {
            it?.let {
                pbProfileLoading.visibility = View.GONE
                context?.showToast(it)
            }
        })
    }

    private fun fillUserInfo(customer: Customer) {
        GlideApp
            .with(this)
            .load("$BASE_URL_PROFILE_ICON${customer.profileIconId}.png")
            .into(civProfileSummonerIcon)

        etProfileSummonerName.text = Editable.Factory.getInstance().newEditable(customer.summonerName)
        etProfileName.text = Editable.Factory.getInstance().newEditable(customer.name)
        tilProfileEmail.editText?.text = Editable.Factory.getInstance().newEditable(customer.email)
        swProfileSystemColorPreference.isChecked = customer.colorPreference == FIELD_USER_COLOR_PREFERENCE_DARK
    }

    private fun getHashMapProfile(): HashMap<String, Any> {
        return hashMapOf(
            FIELD_USER_NAME to etProfileName.text.toString(),
            FIELD_USER_SUMMONER_NAME to etProfileSummonerName.text.toString(),
            FIELD_USER_COLOR_PREFERENCE to
                    if (swProfileSystemColorPreference.isChecked)
                        FIELD_USER_COLOR_PREFERENCE_DARK
                    else
                        FIELD_USER_COLOR_PREFERENCE_LIGHT
        )
    }
}