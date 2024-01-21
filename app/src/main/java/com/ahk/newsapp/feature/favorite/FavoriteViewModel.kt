package com.ahk.newsapp.feature.favorite

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.ahk.newsapp.app.repository.FetchBookmarkedArticlesUseCase
import com.ahk.newsapp.base.domain.CustomException
import com.ahk.newsapp.base.domain.asCustomException
import com.ahk.newsapp.base.ui.FragmentUIEvent
import com.ahk.newsapp.base.ui.FragmentUIState
import com.ahk.newsapp.base.ui.FragmentViewModel
import com.ahk.newsapp.feature.home_page.model.ArticleEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val fetchBookmarkedArticles: FetchBookmarkedArticlesUseCase,
) : FragmentViewModel<FavoriteUIEvent, FavoriteUIState>() {
    fun init() {
        setState(
            FavoriteUIState.Success(),
        )
        viewModelScope.launch {
            try {
                fetchBookmarkedArticles().collect {
                    setState(
                        FavoriteUIState.Success(
                            articles = it,
                        ),
                    )
                }
            } catch (exception: Exception) {
                setState(
                    FavoriteUIState.Error(
                        exception = exception.asCustomException(),
                    ),
                )
            }
        }
    }

    fun onArticleClicked(article: ArticleEntity) {
        setEvent(
            FavoriteUIEvent.OnArticleClicked(
                article = article,
            ),
        )
    }
}

sealed class FavoriteUIState : FragmentUIState {
    data class Success(
        val articles: PagingData<ArticleEntity> = PagingData.empty(),
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

sealed class FavoriteUIEvent : FragmentUIEvent {
    data class OnArticleClicked(
        val article: ArticleEntity,
    ) : FavoriteUIEvent()
}
