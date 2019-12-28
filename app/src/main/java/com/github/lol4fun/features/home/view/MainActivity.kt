package com.github.lol4fun.features.home.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.github.lol4fun.R
import com.github.lol4fun.features.home.viewmodel.MainViewModel
import com.github.lol4fun.util.ConstantsUtil.Main.RC_SIGN_IN
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import android.app.Activity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.github.lol4fun.features.nickname.view.NicknameActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by lazy { MainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkIfUserIsLogged()
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navController = navHostFragment.findNavController()
        bottomNav.setupWithNavController(navController)
    }

    private fun checkIfUserIsLogged() {
        if (mainViewModel.userIsLogged()) {
            // already signed in
            //TODO tela principal
        } else {
            startLoginActivity()
        }
    }

    private fun startLoginActivity() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(
                    mainViewModel.getListOfProviders()
                )
                .build(),
            RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            // Successfully signed in
            if (resultCode == Activity.RESULT_OK) {
                mainViewModel.saveUserFirestore()
                startActivity(Intent(this, NicknameActivity::class.java))
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
//                    showSnackBar(R.string.sign_in_canceled_by_user)
                    return
                }

                if (response.error!!.errorCode == ErrorCodes.NO_NETWORK) {
//                    showSnackbar(R.string.no_internet_connection)
                    return
                }

//                showSnackbar(R.string.unknown_error)
//                Log.e(FragmentActivity.TAG, "Sign-in error: ", response.error)
            }
        }
    }
}
