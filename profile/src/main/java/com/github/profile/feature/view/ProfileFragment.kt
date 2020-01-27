package com.github.profile.feature.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.github.lol4fun.core.model.Customer
import com.github.lol4fun.extensions.getTextByEditable
import com.github.lol4fun.extensions.showSnackBar
import com.github.lol4fun.extensions.showToast
import com.github.lol4fun.features.nickname.view.NicknameActivity
import com.github.lol4fun.util.ConstantsUtil.Api.BASE_URL_PROFILE_ICON
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_COLOR_PREFERENCE
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_COLOR_PREFERENCE_DARK
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_COLOR_PREFERENCE_LIGHT
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_NAME
import com.github.lol4fun.util.ConstantsUtil.FirestoreDataBaseFields.FIELD_USER_SUMMONER_NAME
import com.github.lol4fun.util.ConstantsUtil.Main.RC_SIGN_IN
import com.github.lol4fun.util.GlideApp
import com.github.profile.R
import com.github.profile.di.ProfileDependencyInjection
import com.github.profile.feature.viewmodel.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth
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

    override fun onResume() {
        super.onResume()

        val isAnonymous = viewModel.isAnonymous()

        if (isAnonymous == true) {
            pbProfileLoading.visibility = View.GONE
            vgProfileContent.visibility = View.GONE

            startUpgradeAnonymousAccount()
        } else {
            fillUserInformation()
        }
    }

    private fun startUpgradeAnonymousAccount() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .enableAnonymousUsersAutoUpgrade()
                .setAvailableProviders(
                    viewModel.getListOfProviders()
                )
                .build(),
            RC_SIGN_IN
        )
    }

    private fun fillUserInformation() {
        val customer = viewModel.onSuccessGetUserFirestore.value

        if (customer == null) {
            viewModel.getUserFirestore()
            setupObservableGetUserFirestore()
        } else {
            fillUserInfo(customer)
            setupVisibilityMainContent()
        }

        setupObservables()
    }

    private fun setupVisibilityMainContent(){
        vgProfileContent.visibility = View.VISIBLE
        pbProfileLoading.visibility = View.GONE
    }

    private fun setupObservableGetUserFirestore() {
        viewModel.onSuccessGetUserFirestore.observe(viewLifecycleOwner, Observer {
            setupVisibilityMainContent()
            it?.let {
                fillUserInfo(it, true)
            }
        })
    }

    private fun setupObservables() {
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

        swProfileSystemColorPreference.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            viewModel.saveColorPreferenceFirestore(
                if (isChecked)
                    FIELD_USER_COLOR_PREFERENCE_DARK
                else
                    FIELD_USER_COLOR_PREFERENCE_LIGHT
            )
        }
    }

    private fun fillUserInfo(customer: Customer, fillSystemColor: Boolean = false) {
        GlideApp
            .with(this)
            .load("$BASE_URL_PROFILE_ICON${customer.profileIconId}.png")
            .into(civProfileSummonerIcon)

        etProfileSummonerName?.text = customer.summonerName?.getTextByEditable()
        etProfileName?.text = customer.name?.getTextByEditable()
        tilProfileEmail?.editText?.text = customer.email?.getTextByEditable()

        if (fillSystemColor) {
            swProfileSystemColorPreference?.isChecked =
                customer.colorPreference == FIELD_USER_COLOR_PREFERENCE_DARK
        }
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            // Successfully signed in
            if (resultCode == Activity.RESULT_OK) {
                viewModel.saveUserFirestore()
                startActivity(Intent(context, NicknameActivity::class.java))
            } else {
                if (response == null) {
                    return
                } else if (response.error?.errorCode == ErrorCodes.ANONYMOUS_UPGRADE_MERGE_CONFLICT) {
                    signInWithCredential(response)
                }
            }
        }
    }

    private fun signInWithCredential(response: IdpResponse) {
        // Get the non-anoymous credential from the response
        val nonAnonymousCredential = response.credentialForLinking
        // Sign in with credential
        nonAnonymousCredential?.let { authCredential ->
            viewModel.signInWithCredential(authCredential)
        }
    }
}