package com.ahk.newsapp.feature.home_page

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahk.newsapp.R
import com.ahk.newsapp.base.ui.BaseFragment
import com.ahk.newsapp.base.ui.BaseListAdapter
import com.ahk.newsapp.base.ui.setup
import com.ahk.newsapp.databinding.ArticleCategoryButtonItemBinding
import com.ahk.newsapp.databinding.ArticleListItemBinding
import com.ahk.newsapp.databinding.FragmentHomePageBinding
import com.ahk.newsapp.feature.home_page.model.ArticleEntity
import com.ahk.newsapp.feature.home_page.model.CategoryButtonData
import com.ahk.newsapp.feature.home_page.model.SourceEntity
import dagger.hilt.android.AndroidEntryPoint
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
    override val viewModel: HomePageViewModel = HomePageViewModel()

    private lateinit var articleAdapter: BaseListAdapter<ArticleEntity, ArticleListItemBinding>
    private lateinit var articleTypeButtons: BaseListAdapter<CategoryButtonData, ArticleCategoryButtonItemBinding>

    private val dummyArticleList = listOf(
        ArticleEntity(
            description = "description",
            source = SourceEntity(
                name = "name",
            ),
            title = "title",
            url = "url",
            urlToImage = "urlToImage",
            content = "content",
        ),
        ArticleEntity(
            description = "description",
            source = SourceEntity(
                name = "name",
            ),
            title = "title",
            url = "url",
            urlToImage = "urlToImage",
            content = "content",
        ),
        ArticleEntity(
            description = "description",
            source = SourceEntity(
                name = "name",
            ),
            title = "title",
            url = "url",
            urlToImage = "urlToImage",
            content = "content",
        ),
        ArticleEntity(
            description = "description",
            source = SourceEntity(
                name = "name",
            ),
            title = "title",
            url = "url",
            urlToImage = "urlToImage",
            content = "content",
        ),
        ArticleEntity(
            description = "description",
            source = SourceEntity(
                name = "name",
            ),
            title = "title",
            url = "url",
            urlToImage = "urlToImage",
            content = "content",
        ),
    )

    private val buttonDummyList = listOf(
        CategoryButtonData(
            name = "name",
            isSelected = false,
        ),
        CategoryButtonData(
            name = "name",
            isSelected = false,
        ),
        CategoryButtonData(
            name = "name",
            isSelected = false,
        ),
        CategoryButtonData(
            name = "name",
            isSelected = false,
        ),
        CategoryButtonData(
            name = "name",
            isSelected = true,
        ),
    )

    override fun initView(binding: FragmentHomePageBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        articleAdapter = object : BaseListAdapter<ArticleEntity, ArticleListItemBinding>() {
            override fun getLayoutId() = R.layout.article_list_item
            override fun setUIState(binding: ArticleListItemBinding, item: ArticleEntity) {
                binding.articleListItemEntity = item
            }
        }
        articleAdapter.onItemClickListener =
            BaseListAdapter.OnItemClickListener { articleListItemEntity, _ ->
                Timber.d("onItemClickListener: $articleListItemEntity")
            }

        articleAdapter.onItemLongClickListener =
            BaseListAdapter.OnItemLongClickListener { articleListItemEntity, _ ->
                Timber.d("onItemLongClickListener: $articleListItemEntity")
            }

        binding.rvPopularNews.setup(
            articleAdapter,
        )
        articleAdapter.setItems(dummyArticleList)

        articleTypeButtons =
            object : BaseListAdapter<CategoryButtonData, ArticleCategoryButtonItemBinding>() {
                override fun getLayoutId() = R.layout.article_category_button_item
                override fun setUIState(
                    binding: ArticleCategoryButtonItemBinding,
                    item: CategoryButtonData,
                ) {
                    binding.categoryButtonData = item
                }
            }
        articleTypeButtons.onItemClickListener =
            BaseListAdapter.OnItemClickListener { categoryButtonData, _ ->
                Timber.d("onItemClickListener: $categoryButtonData")
            }

        binding.rvNewTypes.setup(
            articleTypeButtons,
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false,
            ),

        )
        articleTypeButtons.setItems(buttonDummyList)
    }

    override fun setBindingViewModel() {
        mBinding!!.viewModel = viewModel
    }

    override fun handleArgs(args: Bundle) {
        Timber.d("handleArgs: $args")
    }

    override fun handleUIState(it: HomePageUIState) {
        Timber.d("handleUIState: $it")
    }

    override fun handleUIEvent(it: HomePageUIEvent) {
        Timber.d("handleUIEvent: $it")
    }
}
