package com.petproject.chesstimerclone.presentation.presenter

data class HomeStates(
    val whiteMoved: Int = 0,
    val blackMoved: Int = 0,

    val whiteMove: Boolean = false,
    val blackMove: Boolean = false,

    val whiteSectionWeight: Float = 1f,
    val blackSectionWeight: Float = 1f,

    val isWhiteTimerStarted : Boolean = false,
    val isBlackTimerStarted: Boolean = false,

    val isWhiteTimerPaused : Boolean = false,
    val isBlackTimerPaused: Boolean = false,

    val isReset : Boolean = false,
    val isGeneralPause: Boolean = false,
    val isMatchStarted: Boolean = false,

    val initialTimerValue: Long = 10L,
    val blackTime: Long = initialTimerValue,
    val whiteTime: Long = initialTimerValue,
)
