package com.github.lol4fun.features.nickname.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.lol4fun.R
import com.github.lol4fun.extensions.hideKeyboard
import com.github.lol4fun.extensions.showSnackBar
import com.github.lol4fun.extensions.showToast
import com.github.lol4fun.features.nickname.viewmodel.NicknameViewModel
import kotlinx.android.synthetic.main.fragment_nickname.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NicknameFragment : Fragment() {

    private val viewModel: NicknameViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nickname, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.onErrorSummonerNameLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                pbNicknameLoading.visibility = View.GONE
                context?.showSnackBar(mbNicknameContinue, it)
            }
        })

        viewModel.onErrorGetSummonerNameApiLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                pbNicknameLoading.visibility = View.GONE
                context?.showSnackBar(mbNicknameContinue, it)
            }
        })

        viewModel.onErrorSaveSummonerInfoFirestoreLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                pbNicknameLoading.visibility = View.GONE
                context?.showSnackBar(mbNicknameContinue, it)
            }
        })

        viewModel.onSuccessSaveSummonerInfoLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    pbNicknameLoading.visibility = View.GONE
                    context?.showToast(getString(R.string.nickname_success_save_summoner_infos))
                    findNavController().navigate(R.id.nickname_to_home)
                    activity?.finish()
                }
            }
        })
    }

    private fun setupView() {
        mbNicknameContinue.setOnClickListener {
            pbNicknameLoading.visibility = View.VISIBLE
            context?.hideKeyboard(tilNicknameSummonerName)
            viewModel.verifySummonerNameAndSave(tilNicknameSummonerName.editText?.text.toString())
        }
    }
}