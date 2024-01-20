package com.ahk.newsapp.feature.home_page

import android.os.Bundle
import com.ahk.newsapp.R
import com.ahk.newsapp.base.ui.BaseFragment
import com.ahk.newsapp.databinding.FragmentHomePageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePage : BaseFragment<FragmentHomePageBinding, HomePageUIEvent, HomePageUIState, HomePageViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_home_page
    override val titleId: Int
        get() = R.string.home_page_title
    override val fragmentTag: String
        get() = "HomePage"
    override val viewModel: HomePageViewModel = HomePageViewModel()

    override fun setBindingViewModel() {
        TODO("Not yet implemented")
    }

    override fun handleArgs(args: Bundle) {
        TODO("Not yet implemented")
    }

    override fun handleUIState(it: HomePageUIState) {
        TODO("Not yet implemented")
    }

    override fun handleUIEvent(it: HomePageUIEvent) {
        TODO("Not yet implemented")
    }

    override fun initView(binding: FragmentHomePageBinding) {
        TODO("Not yet implemented")

    }
}