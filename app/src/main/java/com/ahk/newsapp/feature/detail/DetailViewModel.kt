package com.ahk.newsapp.feature.detail

import com.ahk.newsapp.base.domain.CustomException
import com.ahk.newsapp.base.ui.FragmentUIEvent
import com.ahk.newsapp.base.ui.FragmentUIState
import com.ahk.newsapp.base.ui.FragmentViewModel
import com.ahk.newsapp.feature.home_page.model.ArticleEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : FragmentViewModel<DetailUIEvent, DetailUIState>() {
    fun initState(articleEntity: ArticleEntity) {
        setState(
            DetailUIState.Success(
                articleEntity = articleEntity,
                hasArticleContent = articleEntity.content.isNotEmpty(),
            ),
        )
    }
}

sealed class DetailUIState : FragmentUIState {
    data class Success(
        val articleEntity: ArticleEntity = ArticleEntity(),
        val hasArticleContent: Boolean = false,
    ) : DetailUIState()

    data class Error(
        val customException: CustomException?,
    ) : DetailUIState()

    data object Loading : DetailUIState()

    fun isSuccess() = this is Success
    fun isError() = this is Error
    fun isLoading() = this is Loading

    fun getSuccessArticleEntity(): ArticleEntity? {
        return if (this is Success) {
            articleEntity
        } else {
            null
        }
    }

    fun getException(): CustomException? {
        return if (this is Error) {
            customException
        } else {
            null
        }
    }

    fun getHasContent(): Boolean? {
        return if (this is Success) {
            hasArticleContent
        } else {
            null
        }
    }
}

sealed interface DetailUIEvent : FragmentUIEvent
