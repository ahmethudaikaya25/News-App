package com.ahk.newsapp.feature.favorite

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.ahk.newsapp.R
import com.ahk.newsapp.base.ui.BaseFragment
import com.ahk.newsapp.base.ui.BaseListAdapter
import com.ahk.newsapp.databinding.ArticleListItemBinding
import com.ahk.newsapp.databinding.FragmentFavoriteBinding
import com.ahk.newsapp.feature.home_page.model.ArticleEntity
import com.ahk.newsapp.feature.home_page.model.SourceEntity
import dagger.hilt.android.AndroidEntryPoint

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

    lateinit var adapter: BaseListAdapter<ArticleEntity, ArticleListItemBinding>

    val favoriteArticles = listOf(
        ArticleEntity(
            description = "1",
            source = SourceEntity("source"),
            title = "title",
            url = "url",
            urlToImage = "urlToImage",
            content = "content",
        ),
        ArticleEntity(
            description = "2",
            source = SourceEntity("source"),
            title = "title",
            url = "url",
            urlToImage = "urlToImage",
            content = "content",
        ),
        ArticleEntity(
            description = "3",
            source = SourceEntity("source"),
            title = "title",
            url = "url",
            urlToImage = "urlToImage",
            content = "content",
        ),
        ArticleEntity(
            description = "4",
            source = SourceEntity("source"),
            title = "title",
            url = "url",
            urlToImage = "urlToImage",
            content = "content",
        ),
        ArticleEntity(
            description = "5",
            source = SourceEntity("source"),
            title = "title",
            url = "url",
            urlToImage = "urlToImage",
            content = "content",
        ),
    )

    override fun initView(binding: FragmentFavoriteBinding) {
        adapter = object : BaseListAdapter<ArticleEntity, ArticleListItemBinding>() {
            override fun getLayoutId(): Int {
                return R.layout.article_list_item
            }

            override fun setUIState(binding: ArticleListItemBinding, item: ArticleEntity) {
                binding.articleListItemEntity = item
            }
        }

        mBinding?.let {
            val favoriteNews = it.rvFavoriteNews
            favoriteNews.adapter = adapter
        } ?: return
        adapter.setItems(favoriteArticles)
    }

    override fun setBindingViewModel() {
        mBinding?.viewModel = viewModel
    }

    override fun handleArgs(args: Bundle) {
        TODO("Not yet implemented")
    }

    override fun handleUIState(it: FavoriteUIState) {
        TODO("Not yet implemented")
    }

    override fun handleUIEvent(it: FavoriteUIEvent) {
        TODO("Not yet implemented")
    }
}
