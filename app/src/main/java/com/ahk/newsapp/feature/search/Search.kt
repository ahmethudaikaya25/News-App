package com.ahk.newsapp.feature.search

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahk.newsapp.R
import com.ahk.newsapp.base.ui.BaseFragment
import com.ahk.newsapp.databinding.FragmentSearchBinding
import com.ahk.newsapp.feature.adapter.ArticleListAdapter
import com.ahk.newsapp.feature.home_page.model.ArticleEntity
import com.ahk.newsapp.feature.util.ItemClickListener
import dagger.hilt.android.AndroidEntryPoint

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
            onBookmarkClickListener = object : ItemClickListener<ArticleEntity> {
                override fun onClicked(data: ArticleEntity) {
                    viewModel.bookmarkClicked(data)
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

            else -> {}
        }
    }

    override fun handleUIEvent(it: SearchUIEvent) {
        when (it) {
            is SearchUIEvent.OnArticleItemClicked -> {
                findNavController().navigate(
                    SearchDirections.actionSearchToDetail(it.articleEntity),
                )
            }
        }
    }

    override fun onDestroyView() {
        mBinding?.searchResultRecyclerView?.adapter = null
        super.onDestroyView()
    }
}
