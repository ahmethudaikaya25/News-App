package com.ahk.newsapp.feature.search

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.ahk.newsapp.R
import com.ahk.newsapp.base.ui.BaseFragment
import com.ahk.newsapp.databinding.FragmentSearchBinding
import com.ahk.newsapp.feature.adapter.ArticleListAdapter
import com.ahk.newsapp.feature.home_page.model.ArticleEntity
import com.ahk.newsapp.feature.util.ItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class Search :
    BaseFragment<FragmentSearchBinding, SearchUIEvent, SearchUIState, SearchViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_search
    override val titleId: Int
        get() = R.string.search
    override val viewModel: SearchViewModel by viewModels()
    override val fragmentTag: String
        get() = "Search"

    lateinit var adapter: ArticleListAdapter

    override fun initView(binding: FragmentSearchBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        adapter = ArticleListAdapter(
            itemClickListener = object : ItemClickListener<ArticleEntity> {
                override fun onClicked(data: ArticleEntity) {
                    viewModel.articleItemClicked(data)
                }
            },
        )
        binding.searchResultRecyclerView.adapter = adapter
    }

    override fun setBindingViewModel() {
        mBinding?.viewModel = viewModel
    }

    override fun handleArgs(args: Bundle) {
        TODO("Not yet implemented")
    }

    override fun handleUIState(it: SearchUIState) {
        when (it) {
            is SearchUIState.Success -> {
                adapter.submitData(lifecycle, it.articles)
            }

            is SearchUIState.Loading -> {
                Timber.d("Loading: ${it.formData}")
            }

            else -> {}
        }
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
