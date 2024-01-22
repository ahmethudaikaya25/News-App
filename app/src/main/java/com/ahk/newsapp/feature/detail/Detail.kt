package com.ahk.newsapp.feature.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.ahk.newsapp.R
import com.ahk.newsapp.base.ui.BaseFragment
import com.ahk.newsapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    override fun setBindingViewModel() {
        mBinding!!.viewModel = viewModel
    }

    override fun handleArgs(args: Bundle) {
        viewModel.initState(DetailArgs.fromBundle(args).article)
    }

    override fun handleUIState(it: DetailUIState) {
        Timber.d("handleUIState: $it")
    }

    override fun handleUIEvent(it: DetailUIEvent) {
        Timber.d("handleUIEvent: $it")
    }
}
