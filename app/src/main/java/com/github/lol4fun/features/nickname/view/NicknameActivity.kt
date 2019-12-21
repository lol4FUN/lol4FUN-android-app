package com.github.lol4fun.features.nickname.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.github.lol4fun.R
import com.github.lol4fun.extensions.showSnackBar
import com.github.lol4fun.features.nickname.viewmodel.NicknameViewModel
import kotlinx.android.synthetic.main.activity_nickname.*

class NicknameActivity : AppCompatActivity() {

    private val viewModel: NicknameViewModel by lazy { NicknameViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nickname)

        setupView()
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.onErrorSummonerNameLiveData.observe(this, Observer {
            it?.let {
                showSnackBar(mbNicknameContinue, it)
            }
        })
    }

    private fun setupView() {
        mbNicknameContinue.setOnClickListener {
            viewModel.verifySummonerNameAndSave(tilNicknameSummonerName.editText?.text.toString())
        }
    }
}
