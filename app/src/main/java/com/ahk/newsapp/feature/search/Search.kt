package com.ahk.newsapp.feature.search

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.ahk.newsapp.R
import com.ahk.newsapp.base.ui.BaseFragment
import com.ahk.newsapp.base.ui.BaseListAdapter
import com.ahk.newsapp.base.ui.setup
import com.ahk.newsapp.databinding.ArticleListItemBinding
import com.ahk.newsapp.databinding.FragmentSearchBinding
import com.ahk.newsapp.feature.home_page.model.ArticleEntity
import com.ahk.newsapp.feature.home_page.model.SourceEntity
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

    lateinit var adapter: BaseListAdapter<ArticleEntity, ArticleListItemBinding>

    val dummyRecyclerList = listOf(
        ArticleEntity(
            description = "description",
            source = SourceEntity("name"),
            title = "title",
            url = "url",
            urlToImage = "urlToImage",
            content = "content",
        ),
        ArticleEntity(
            description = "description",
            source = SourceEntity("name"),
            title = "title",
            url = "url",
            urlToImage = "urlToImage",
            content = "content",
        ),
        ArticleEntity(
            description = "description",
            source = SourceEntity("name"),
            title = "title",
            url = "url",
            urlToImage = "urlToImage",
            content = "content",
        ),
        ArticleEntity(
            description = "description",
            source = SourceEntity("name"),
            title = "title",
            url = "url",
            urlToImage = "urlToImage",
            content = "content",
        ),
        ArticleEntity(
            description = "description",
            source = SourceEntity("name"),
            title = "title",
            url = "url",
            urlToImage = "urlToImage",
            content = "content",
        ),
        ArticleEntity(
            description = "description",
            source = SourceEntity("name"),
            title = "title",
            url = "url",
            urlToImage = "urlToImage",
            content = "content",
        ),
    )

    override fun initView(binding: FragmentSearchBinding) {
        adapter = object : BaseListAdapter<ArticleEntity, ArticleListItemBinding>() {
            override fun getLayoutId(): Int {
                return R.layout.article_list_item
            }

            override fun setUIState(binding: ArticleListItemBinding, item: ArticleEntity) {
                binding.articleListItemEntity = item
            }
        }
        adapter.onItemClickListener = BaseListAdapter.OnItemClickListener { item, _ ->
            viewModel.articleItemClicked(item)
        }
        binding.searchResultRecyclerView.setup(
            adapter = adapter,
        )
        adapter.setItems(dummyRecyclerList)
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
