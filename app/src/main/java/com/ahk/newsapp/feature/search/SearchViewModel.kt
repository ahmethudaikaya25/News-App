package com.ahk.newsapp.feature.search

import android.text.Editable
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.ahk.newsapp.app.repository.SearchArticlesUseCase
import com.ahk.newsapp.app.repository.ToggleBookmarkUseCase
import com.ahk.newsapp.base.domain.CustomException
import com.ahk.newsapp.base.domain.asCustomException
import com.ahk.newsapp.base.ui.FragmentUIEvent
import com.ahk.newsapp.base.ui.FragmentUIState
import com.ahk.newsapp.base.ui.FragmentViewModel
import com.ahk.newsapp.feature.home_page.model.ArticleEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val searchArticlesUseCase: SearchArticlesUseCase,
    val toggleBookmarkUseCase: ToggleBookmarkUseCase,
) : FragmentViewModel<SearchUIEvent, SearchUIState>() {
    var lastFormData: SearchFormData = SearchFormData()
    var searchJob: Job? = null

    override fun setState(state: SearchUIState) {
        lastFormData = when (state) {
            is SearchUIState.Success -> {
                state.formData
            }

            is SearchUIState.Loading -> {
                state.formData
            }

            else -> {
                lastFormData
            }
        }
        super.setState(state)
    }

    init {
        setState(SearchUIState.Success())
    }

    fun articleItemClicked(item: ArticleEntity) {
        setEvent(SearchUIEvent.OnArticleItemClicked(item))
    }

    fun onSearchTextChanged(editable: Editable?) {
        val text = editable.toString()
        if (searchJob != null && searchJob?.isActive == true) {
            searchJob?.cancel()
        }

        searchJob = viewModelScope.launch {
            setState(SearchUIState.Loading(lastFormData))
            if (text.length < 3) {
                setState(
                    SearchUIState.Success(
                        SearchFormData(
                            query = text,
                        ),
                        PagingData.empty(),
                    ),
                )
                return@launch
            }
            delay(200)
            try {
                searchArticlesUseCase(text, viewModelScope).collect {
                    setState(
                        SearchUIState.Success(
                            SearchFormData(
                                query = text,
                            ),
                            it,
                        ),
                    )
                }
            } catch (e: Exception) {
                setState(SearchUIState.Error(e.asCustomException()))
            }
        }
    }

    fun bookmarkClicked(data: ArticleEntity) {
        viewModelScope.launch {
            try {
                toggleBookmarkUseCase(data)
                searchArticlesUseCase(lastFormData.query, viewModelScope).collect {
                    setState(
                        SearchUIState.Success(
                            SearchFormData(
                                query = lastFormData.query,
                            ),
                            it,
                        ),
                    )
                }
            } catch (exception: Exception) {
                setState(
                    SearchUIState.Error(
                        exception = exception.asCustomException(),
                    ),
                )
            }
        }
    }
}

sealed interface SearchUIState : FragmentUIState {
    data class Success(
        val formData: SearchFormData = SearchFormData(),
        val articles: PagingData<ArticleEntity> = PagingData.empty(),
    ) : SearchUIState

    data class Loading(
        val formData: SearchFormData = SearchFormData(),
    ) : SearchUIState

    data class Error(
        val exception: CustomException,
    ) : SearchUIState

    data class TemporaryError(
        val exception: CustomException,
    ) : SearchUIState

    fun isSuccess() = this is Success

    fun isError() = this is Error

    fun isTemporaryError() = this is TemporaryError

    fun isLoading() = this is Loading

    fun getSuccessFormData() = if (this is Success) formData else null

    fun getSuccessArticles() = if (this is Success) articles else null

    fun getErrorException() = if (this is Error) exception else null

    fun getTemporaryErrorException() = if (this is TemporaryError) exception else null
}

sealed interface SearchUIEvent : FragmentUIEvent {
    data class OnArticleItemClicked(
        val articleEntity: ArticleEntity,
    ) : SearchUIEvent
}

data class SearchFormData(
    val query: String = "",
)
