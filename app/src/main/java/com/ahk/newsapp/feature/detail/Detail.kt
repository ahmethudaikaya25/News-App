package com.ahk.newsapp.feature.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.ahk.newsapp.R
import com.ahk.newsapp.base.ui.BaseFragment
import com.ahk.newsapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Detail :
    BaseFragment<FragmentDetailBinding, DetailUIEvent, DetailUIState, DetailViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_detail
    override val titleId: Int
        get() = R.string.detail
    override val fragmentTag: String
        get() = "Detail"
    override val viewModel: DetailViewModel by viewModels()

    override fun initView(binding: FragmentDetailBinding) {
    }

    override fun setBindingViewModel() {
        mBinding!!.viewModel = viewModel
    }

    override fun handleArgs(args: Bundle) {
    }

    override fun handleUIState(it: DetailUIState) {
        TODO("Not yet implemented")
    }

    override fun handleUIEvent(it: DetailUIEvent) {
        TODO("Not yet implemented")
    }
}
