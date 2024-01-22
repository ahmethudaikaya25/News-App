package com.ahk.newsapp.feature.detail

import com.ahk.newsapp.R
import com.ahk.newsapp.base.domain.CustomException
import com.ahk.newsapp.base.domain.CustomExceptionData
import com.ahk.newsapp.base.ui.FragmentUIEvent
import com.ahk.newsapp.base.ui.FragmentUIState
import com.ahk.newsapp.base.ui.FragmentViewModel
import com.ahk.newsapp.feature.home_page.model.ArticleEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : FragmentViewModel<DetailUIEvent, DetailUIState>() {
    fun initState(articleEntity: ArticleEntity) {
        val url = articleEntity.url
        val content = articleEntity.content
        setState(DetailUIState.Loading)
        if (content.isNullOrEmpty()) {
            if (url.isNullOrEmpty()) {
                setState(
                    DetailUIState.Error(
                        customException = CustomException.NotValidParametersException(
                            CustomExceptionData(
                                title = R.string.news_could_not_be_opened,
                                message = R.string.url_is_not_valid,
                            ),
                        ),
                    ),
                )
                return
            }
            setState(
                DetailUIState.Success(
                    articleEntity = articleEntity,
                    hasArticleContent = false,
                ),
            )
        } else {
            setState(
                DetailUIState.Success(
                    articleEntity = articleEntity,
                    hasArticleContent = true,
                ),
            )
        }
    }
}

sealed class DetailUIState : FragmentUIState {
    data class Success(
        val articleEntity: ArticleEntity = ArticleEntity(),
        val hasArticleContent: Boolean = !articleEntity.content.isNullOrEmpty(),
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
