package com.ahk.newsapp.feature.search

import com.ahk.newsapp.base.domain.CustomException
import com.ahk.newsapp.base.ui.FragmentUIEvent
import com.ahk.newsapp.base.ui.FragmentUIState
import com.ahk.newsapp.base.ui.FragmentViewModel
import com.ahk.newsapp.feature.home_page.model.ArticleEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : FragmentViewModel<SearchUIEvent, SearchUIState>() {
    var lastFormData: SearchFormData = SearchFormData()

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

    fun articleItemClicked(item: ArticleEntity) {
        setEvent(SearchUIEvent.OnArticleItemClicked(item))
    }

    fun onSearchTextChanged(query: CharSequence) {
        // make search requests after 1 second of user inactivity
    }
}

sealed interface SearchUIState : FragmentUIState {
    data class Success(
        val formData: SearchFormData = SearchFormData(),
        val articles: List<ArticleEntity> = emptyList(),
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

    fun isSuccessful() = this is Success

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
