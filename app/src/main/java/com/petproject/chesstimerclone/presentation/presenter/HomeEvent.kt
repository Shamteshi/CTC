package com.petproject.chesstimerclone.presentation.presenter

sealed class HomeEvent {
    data object WhiteMoved : HomeEvent()
    data object BlackMoved : HomeEvent()
    data object Reset : HomeEvent()

    data object MatchStarted: HomeEvent()

    data object PauseMatch: HomeEvent()

}