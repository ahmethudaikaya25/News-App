package com.ahk.newsapp.base.ui

abstract class FragmentViewModel<UE : FragmentUIEvent, US : FragmentUIState> :
    BaseViewModel<UE, US>()

interface FragmentUIState : BaseUIState

interface FragmentUIEvent : BaseUIEvent
