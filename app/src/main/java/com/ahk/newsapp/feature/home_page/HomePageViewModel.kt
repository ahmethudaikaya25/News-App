package com.ahk.newsapp.feature.home_page

import com.ahk.newsapp.base.ui.FragmentUIEvent
import com.ahk.newsapp.base.ui.FragmentUIState
import com.ahk.newsapp.base.ui.FragmentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor() :
    FragmentViewModel<HomePageUIEvent, HomePageUIState>()

sealed class HomePageUIState : FragmentUIState {
    object Loading : HomePageUIState()
    object Error : HomePageUIState()
    object Success : HomePageUIState()
}

sealed class HomePageUIEvent : FragmentUIEvent {
    object OnLoad : HomePageUIEvent()
    object OnError : HomePageUIEvent()
    object OnSuccess : HomePageUIEvent()
}
