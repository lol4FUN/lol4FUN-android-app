package com.github.lol4fun.features.nickname.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.github.lol4fun.R
import com.github.lol4fun.extensions.hideKeyboard
import com.github.lol4fun.extensions.showSnackBar
import com.github.lol4fun.extensions.showToast
import com.github.lol4fun.features.nickname.viewmodel.NicknameViewModel
import kotlinx.android.synthetic.main.activity_nickname.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NicknameActivity : AppCompatActivity() {

    private val viewModel: NicknameViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nickname)

        setupView()
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.onErrorSummonerNameLiveData.observe(this, Observer {
            it?.let {
                pbNicknameLoading.visibility = View.GONE
                showSnackBar(mbNicknameContinue, it)
            }
        })

        viewModel.onErrorGetSummonerNameApiLiveData.observe(this, Observer {
            it?.let {
                pbNicknameLoading.visibility = View.GONE
                showSnackBar(mbNicknameContinue, it)
            }
        })

        viewModel.onErrorSaveSummonerInfoFirestoreLiveData.observe(this, Observer {
            it?.let {
                pbNicknameLoading.visibility = View.GONE
                showSnackBar(mbNicknameContinue, it)
            }
        })

        viewModel.onSuccessSaveSummonerInfoLiveData.observe(this, Observer {
            it?.let {
                if (it) {
                    pbNicknameLoading.visibility = View.GONE
                    showToast(getString(R.string.nickname_success_save_summoner_infos))
                    finish()
                }
            }
        })
    }

    private fun setupView() {
        mbNicknameContinue.setOnClickListener {
            pbNicknameLoading.visibility = View.VISIBLE
            hideKeyboard(tilNicknameSummonerName)
            viewModel.verifySummonerNameAndSave(tilNicknameSummonerName.editText?.text.toString())
        }
    }
}
