package com.ahk.newsapp.feature.home_page

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.ahk.newsapp.R
import com.ahk.newsapp.base.ui.BaseFragment
import com.ahk.newsapp.databinding.FragmentHomePageBinding
import com.ahk.newsapp.feature.adapter.ArticleListAdapter
import com.ahk.newsapp.feature.adapter.CategoryListAdapter
import com.ahk.newsapp.feature.home_page.model.ArticleEntity
import com.ahk.newsapp.feature.home_page.model.CategoryButtonData
import com.ahk.newsapp.feature.util.ItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomePage :
    BaseFragment<FragmentHomePageBinding, HomePageUIEvent, HomePageUIState, HomePageViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_home_page
    override val titleId: Int
        get() = R.string.home_page_title
    override val fragmentTag: String
        get() = "HomePage"
    override val viewModel: HomePageViewModel by viewModels()

    private lateinit var articleListAdapter: ArticleListAdapter
    private lateinit var categoryListAdapter: CategoryListAdapter

    override fun initView(binding: FragmentHomePageBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.init()
        articleListAdapter = ArticleListAdapter(
            itemClickListener = object : ItemClickListener<ArticleEntity> {
                override fun onClicked(article: ArticleEntity) {
                    viewModel.onArticleClicked(article)
                }
            },
        )

        binding.rvPopularNews.adapter = articleListAdapter

        categoryListAdapter = CategoryListAdapter(
            itemClickListener = object : ItemClickListener<CategoryButtonData> {
                override fun onClicked(categoryButtonData: CategoryButtonData) {
                    viewModel.onCategoryButtonClicked(categoryButtonData)
                }
            },
        ).apply {
            categoryList = viewModel.categoryButtonList
        }
        binding.rvNewTypes.adapter = categoryListAdapter
    }

    override fun setBindingViewModel() {
        mBinding!!.viewModel = viewModel
    }

    override fun handleArgs(args: Bundle) {
        Timber.d("handleArgs: $args")
    }

    override fun handleUIState(it: HomePageUIState) {
        when (it) {
            is HomePageUIState.Success -> {
                categoryListAdapter.submitList(it.categoryButtonList)
                lifecycleScope.launch {
                    articleListAdapter.submitData(it.getSuccessArticleList() ?: PagingData.empty())
                }
            }

            else -> {
                Timber.d("handleUIState: $it")
            }
        }
    }

    override fun handleUIEvent(it: HomePageUIEvent) {
        when (it) {
            is HomePageUIEvent.OnArticleItemClicked -> {
                viewModel.onArticleClicked(it.articleEntity)
            }

            is HomePageUIEvent.OnCategoryButtonClicked -> {
                if (it.categoryButtonData.isSelected) {
                    return
                }
                viewModel.fetchArticles(it.categoryButtonData.name)
            }
        }
    }
}
