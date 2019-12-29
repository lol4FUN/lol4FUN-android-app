package com.github.lol4fun.features.home.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.github.lol4fun.R
import com.github.lol4fun.features.home.viewmodel.MainViewModel
import com.github.lol4fun.util.ConstantsUtil.Main.RC_SIGN_IN
import com.firebase.ui.auth.IdpResponse
import android.app.Activity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.github.lol4fun.extensions.showSnackBar
import com.github.lol4fun.extensions.showToast
import com.github.lol4fun.features.nickname.view.NicknameActivity
import com.google.firebase.auth.FirebaseAuth
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
        if (!mainViewModel.userIsLogged()) {
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
                //todo remover login do usuário em caso de erro na insercao no firestore
                //todo e decidir o que fazer
                mainViewModel.saveUserFirestore()
                startActivity(Intent(this, NicknameActivity::class.java))
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener {
                        if (it.isSuccessful) {
                            mainViewModel.saveUserFirestore()
                        }
                    }
                    showToast(getString(R.string.main_sign_in_anonynous))
                    return
                }

                showSnackBar(bottomNav, mainViewModel.getErrorLogin(response.error?.errorCode))

                return
            }
        }
    }
}
