package com.ahk.newsapp.feature.home_page

import com.ahk.newsapp.base.domain.CustomException
import com.ahk.newsapp.base.ui.FragmentUIEvent
import com.ahk.newsapp.base.ui.FragmentUIState
import com.ahk.newsapp.base.ui.FragmentViewModel
import com.ahk.newsapp.feature.home_page.model.ArticleEntity
import com.ahk.newsapp.feature.home_page.model.CategoryButtonData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor() :
    FragmentViewModel<HomePageUIEvent, HomePageUIState>()

sealed class HomePageUIState : FragmentUIState {
    data object Idle : HomePageUIState()
    data object Loading : HomePageUIState()
    data class Error(
        val customException: CustomException,
    ) : HomePageUIState()

    data class Success(
        val categoryButtonList: List<CategoryButtonData> = listOf(),
        val articleList: List<ArticleEntity> = listOf(),
    ) : HomePageUIState()

    fun isError() = this is Error
    fun isLoading() = this is Loading
    fun isSuccess() = this is Success

    fun getException(): CustomException? {
        return if (this is Error) {
            customException
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
