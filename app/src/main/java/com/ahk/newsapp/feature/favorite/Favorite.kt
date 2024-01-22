package com.ahk.newsapp.feature.favorite

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ahk.newsapp.R
import com.ahk.newsapp.base.ui.BaseFragment
import com.ahk.newsapp.databinding.FragmentFavoriteBinding
import com.ahk.newsapp.feature.adapter.ArticleListAdapter
import com.ahk.newsapp.feature.home_page.model.ArticleEntity
import com.ahk.newsapp.feature.util.ItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class Favorite :
    BaseFragment<FragmentFavoriteBinding, FavoriteUIEvent, FavoriteUIState, FavoriteViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_favorite
    override val titleId: Int
        get() = R.string.favorite
    override val viewModel: FavoriteViewModel by viewModels()
    override val fragmentTag: String
        get() = "Favorite"

    private lateinit var adapter: ArticleListAdapter

    override fun initView(binding: FragmentFavoriteBinding) {
        viewModel.init()
        adapter = ArticleListAdapter(
            itemClickListener = object : ItemClickListener<ArticleEntity> {
                override fun onClicked(data: ArticleEntity) {
                    viewModel.onArticleClicked(data)
                }
            },
            onBookmarkClickListener = object : ItemClickListener<ArticleEntity> {
                override fun onClicked(data: ArticleEntity) {
                    viewModel.onBookmarkClicked(data)
                }
            },
        )
        binding.rvFavoriteNews.adapter = adapter
    }

    override fun setBindingViewModel() {
        mBinding?.viewModel = viewModel
    }

    override fun handleArgs(args: Bundle) {
        Timber.d("handleArgs: $args")
    }

    override fun handleUIState(it: FavoriteUIState) {
        when (it) {
            is FavoriteUIState.Success -> {
                lifecycleScope.launch {
                    adapter.submitData(it.articles)
                }
            }

            is FavoriteUIState.Error -> {
                Timber.e(it.exception)
            }

            else -> {
                Timber.d("handleUIState: $it")
            }
        }
    }

    override fun handleUIEvent(it: FavoriteUIEvent) {
        when (it) {
            is FavoriteUIEvent.OnArticleClicked -> {
                findNavController().navigate(
                    FavoriteDirections.actionFavoriteToDetail(
                        article = it.article,
                    ),
                )
            }
        }
    }
}
