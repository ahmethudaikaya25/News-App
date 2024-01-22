package com.ahk.newsapp.feature.home_page

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.ahk.newsapp.app.repository.FetchBreakingArticlesUseCase
import com.ahk.newsapp.app.repository.ToggleBookmarkUseCase
import com.ahk.newsapp.base.domain.CustomException
import com.ahk.newsapp.base.domain.asCustomException
import com.ahk.newsapp.base.ui.FragmentUIEvent
import com.ahk.newsapp.base.ui.FragmentUIState
import com.ahk.newsapp.base.ui.FragmentViewModel
import com.ahk.newsapp.feature.home_page.model.ArticleEntity
import com.ahk.newsapp.feature.home_page.model.CategoryButtonData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    val fetchBreakingArticlesUseCase: FetchBreakingArticlesUseCase,
    val toggleBookmarkUseCase: ToggleBookmarkUseCase,
) : FragmentViewModel<HomePageUIEvent, HomePageUIState>() {
    var categoryButtonList: List<CategoryButtonData> = listOf(
        CategoryButtonData(
            name = "general",
            isSelected = true,
        ),
        CategoryButtonData(
            name = "business",
            isSelected = false,
        ),
        CategoryButtonData(
            name = "entertainment",
            isSelected = false,
        ),
        CategoryButtonData(
            name = "health",
            isSelected = false,
        ),
        CategoryButtonData(
            name = "science",
            isSelected = false,
        ),
        CategoryButtonData(
            name = "sports",
            isSelected = false,
        ),
        CategoryButtonData(
            name = "technology",
            isSelected = false,
        ),
    )

    override fun setState(state: HomePageUIState) {
        if (state is HomePageUIState.Success) {
            categoryButtonList = state.getSuccessCategoryButtonList() ?: categoryButtonList
        }
        super.setState(state)
    }

    fun init() {
        setState(
            HomePageUIState.Success(
                categoryButtonList = categoryButtonList,
            ),
        )
        viewModelScope.launch {
            try {
                fetchBreakingArticlesUseCase().collect {
                    setState(
                        HomePageUIState.Success(
                            articleList = it,
                            categoryButtonList = uiState.value?.getSuccessCategoryButtonList()
                                ?: categoryButtonList,
                        ),
                    )
                }
            } catch (exception: Exception) {
                setState(
                    HomePageUIState.Error(
                        customException = exception.asCustomException(),
                    ),
                )
            }
        }
    }

    fun fetchArticles(category: String) {
        viewModelScope.launch {
            try {
                val categoryButtonList = uiState.value?.getSuccessCategoryButtonList()?.let {
                    it.map { categoryButtonData ->
                        categoryButtonData.copy(isSelected = categoryButtonData.name == category)
                    }
                } ?: categoryButtonList
                setState(
                    HomePageUIState.Loading,
                )
                fetchBreakingArticlesUseCase(category).collect {
                    setState(
                        HomePageUIState.Success(
                            articleList = it,
                            categoryButtonList = categoryButtonList,
                        ),
                    )
                }
            } catch (exception: Exception) {
                setState(
                    HomePageUIState.Error(
                        customException = exception.asCustomException(),
                    ),
                )
            }
        }
    }

    fun onArticleClicked(article: ArticleEntity) {
        setEvent(
            HomePageUIEvent.OnArticleItemClicked(
                articleEntity = article,
            ),
        )
    }

    fun onCategoryButtonClicked(categoryButtonData: CategoryButtonData) {
        setEvent(
            HomePageUIEvent.OnCategoryButtonClicked(
                categoryButtonData = categoryButtonData,
            ),
        )
    }

    fun onBookmarkClicked(article: ArticleEntity) {
        val category = uiState.value?.getSuccessCategoryButtonList()?.first {
            it.isSelected
        }?.name ?: "general"

        viewModelScope.launch {
            try {
                toggleBookmarkUseCase(article)
                fetchBreakingArticlesUseCase(category).collect {
                    setState(
                        HomePageUIState.Success(
                            articleList = it,
                            categoryButtonList = uiState.value?.getSuccessCategoryButtonList()
                                ?: categoryButtonList,
                        ),
                    )
                }
            } catch (exception: Exception) {
                setState(
                    HomePageUIState.Error(
                        customException = exception.asCustomException(),
                    ),
                )
            }
        }
    }
}

sealed class HomePageUIState : FragmentUIState {
    data object Loading : HomePageUIState()
    data class Error(
        val customException: CustomException,
    ) : HomePageUIState()

    data class Success(
        val articleList: PagingData<ArticleEntity> = PagingData.empty(),
        var categoryButtonList: List<CategoryButtonData> = listOf(),
    ) : HomePageUIState()

    fun isError() = this is Error
    fun isLoading() = this is Loading

    fun getException(): CustomException? {
        return if (this is Error) {
            customException
        } else {
            null
        }
    }

    fun getSuccessArticleList(): PagingData<ArticleEntity>? {
        return if (this is Success) {
            articleList
        } else {
            null
        }
    }

    fun getSuccessCategoryButtonList(): List<CategoryButtonData>? {
        return if (this is Success) {
            categoryButtonList
        } else {
            null
        }
    }
}

sealed class HomePageUIEvent : FragmentUIEvent {
    data class OnCategoryButtonClicked(
        val categoryButtonData: CategoryButtonData,
    ) : HomePageUIEvent()

    data class OnArticleItemClicked(
        val articleEntity: ArticleEntity,
    ) : HomePageUIEvent()
}
