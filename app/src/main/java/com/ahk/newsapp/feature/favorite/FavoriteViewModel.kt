package com.ahk.newsapp.feature.favorite

import com.ahk.newsapp.base.domain.CustomException
import com.ahk.newsapp.base.ui.FragmentUIEvent
import com.ahk.newsapp.base.ui.FragmentUIState
import com.ahk.newsapp.base.ui.FragmentViewModel
import com.ahk.newsapp.feature.home_page.model.ArticleEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor() :
    FragmentViewModel<FavoriteUIEvent, FavoriteUIState>()

sealed class FavoriteUIState : FragmentUIState {
    data class Success(
        val articles: List<ArticleEntity> = emptyList(),
    ) : FavoriteUIState()

    data class Error(
        val exception: CustomException,
    ) : FavoriteUIState()

    data object Loading : FavoriteUIState()

    fun isSuccessful() = this is Success

    fun isError() = this is Error

    fun isLoading() = this is Loading

    fun getSuccessArticles() = if (this is Success) articles else null

    fun getErrorException() = if (this is Error) exception else null
}

sealed class FavoriteUIEvent : FragmentUIEvent
