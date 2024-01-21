package com.ahk.newsapp.feature.search

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.ahk.newsapp.R
import com.ahk.newsapp.base.ui.BaseFragment
import com.ahk.newsapp.databinding.FragmentSearchBinding
import timber.log.Timber

class Search :
    BaseFragment<FragmentSearchBinding, SearchUIEvent, SearchUIState, SearchViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_search
    override val titleId: Int
        get() = R.string.search
    override val viewModel: SearchViewModel by viewModels()
    override val fragmentTag: String
        get() = "Search"

    override fun initView(binding: FragmentSearchBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    override fun setBindingViewModel() {
        mBinding?.viewModel ?: return
    }

    override fun handleArgs(args: Bundle) {
        TODO("Not yet implemented")
    }

    override fun handleUIState(it: SearchUIState) {
        TODO("Not yet implemented")
    }

    override fun handleUIEvent(it: SearchUIEvent) {
        when (it) {
            is SearchUIEvent.OnArticleItemClicked -> {
                Timber.d("Article item clicked: ${it.articleEntity}")
                Timber.d("Opening article in browser: ${it.articleEntity.url}")
            }
        }
    }
}
