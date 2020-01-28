package com.github.lol4fun.features.splash.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.github.lol4fun.R
import com.github.lol4fun.extensions.showSnackBar
import com.github.lol4fun.extensions.showToast
import com.github.lol4fun.features.splash.viewmodel.SplashViewModel
import com.github.lol4fun.util.ConstantsUtil
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel.setPreferenceTheme()
        initObservables()
    }

    private fun initObservables() {
        viewModel.isThemeDark.observe(this, Observer { isThemeDark ->
            isThemeDark?.let { setTheme(it) }
        })

        viewModel.canCheckLogin.observe(this, Observer { canCheckLogin ->
            canCheckLogin?.let { checkIfUserIsLogged() }
        })
    }

    private fun setTheme(isDarkTheme: Boolean) {
        if (isDarkTheme) {
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                checkIfUserIsLogged()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                recreate()
            }
        } else {
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
                checkIfUserIsLogged()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                recreate()
            }
        }
    }

    private fun checkIfUserIsLogged() {
        if (!viewModel.userIsLogged()) {
            startLoginActivity()
        } else viewModel.setMainNavigation()
    }

    private fun startLoginActivity() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(
                    viewModel.getListOfProviders()
                )
                .build(),
            ConstantsUtil.Main.RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ConstantsUtil.Main.RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            // Successfully signed in
            if (resultCode == Activity.RESULT_OK) {
                //todo remover login do usuário em caso de erro na insercao no firestore
                //todo e decidir o que fazer
                viewModel.saveUserFirestore()
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener {
                        if (it.isSuccessful) {
                            viewModel.saveUserFirestore()
                        }
                    }
                    showToast(getString(R.string.main_sign_in_anonynous))
                    return
                }

                showSnackBar(bottomNav, viewModel.getErrorLogin(response.error?.errorCode))

                return
            }
        }
    }
}