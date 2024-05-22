package com.petproject.chesstimerclone.presentation.presenter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomePresenter : ViewModel() {

    var state by mutableStateOf(HomeStates())

    private val _whiteTimer = MutableStateFlow(state.initialTimerValue)
    val whiteTimer = _whiteTimer.asStateFlow()

    private var whiteTimerJob: Job? = null

    private val _blackTimer = MutableStateFlow(state.initialTimerValue)
    val blackTimer = _blackTimer.asStateFlow()

    private var blackTimerJob: Job? = null


    fun startWhiteTimer(){
        whiteTimerJob?.cancel()
        whiteTimerJob = viewModelScope.launch {
            while (_whiteTimer.value > 0){
                delay(1000)
                _whiteTimer.value--
            }
        }
    }

    private fun startBlackTimer(){
        blackTimerJob?.cancel()
        blackTimerJob = viewModelScope.launch {
            while (_blackTimer.value > 0){
                delay(1000)
                _blackTimer.value--
            }

        }
    }

    private fun pauseWhiteTimer(){
        state = state.copy(whiteTime = _whiteTimer.value, isWhiteTimerPaused = true)
        whiteTimerJob?.cancel()
    }

    private fun pauseBlackTimer(){
        state = state.copy(blackTime= _blackTimer.value, isBlackTimerPaused = true)
        blackTimerJob?.cancel()
    }

    private fun stopTimer(){
        whiteTimerJob?.cancel()
        blackTimerJob?.cancel()
        _whiteTimer.value = state.initialTimerValue
        _blackTimer.value = state.initialTimerValue
    }

    private fun timeUp(){
        whiteTimerJob?.cancel()
        blackTimerJob?.cancel()
    }

    /*private fun formatTime(seconds: Long): String{
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }*/

    override fun onCleared() {
        super.onCleared()
        whiteTimerJob?.cancel()
        blackTimerJob?.cancel()
    }


    fun onEvent(event: HomeEvent){
        when(event){
            HomeEvent.BlackMoved -> {
                state = state.copy(
                    blackMoved = state.blackMoved.inc(),
                    blackSectionWeight = 0.4f,
                    whiteSectionWeight = 1f,
                    blackMove = true,
                    whiteMove = false,
                )
                if(!state.isWhiteTimerStarted){
                    state = state.copy(isBlackTimerStarted = false, isWhiteTimerStarted = true)
                    //pause timer here
                    startWhiteTimer()
                    pauseBlackTimer()
                }
                if(state.isGeneralPause){
                    state = state.copy(isGeneralPause = false)
                }
            }

            HomeEvent.WhiteMoved -> {
                state = state.copy(
                    whiteMoved = state.whiteMoved.inc(),
                    whiteSectionWeight = 0.4f,
                    blackSectionWeight = 1f,
                    whiteMove = true,
                    blackMove = false,

                )
                if(!state.isBlackTimerStarted){
                    state = state.copy(isWhiteTimerStarted = false, isBlackTimerStarted = true)
                    startBlackTimer()
                    pauseWhiteTimer()
                }
                if(state.isGeneralPause){
                    state = state.copy(isGeneralPause = false)
                }

            }

            HomeEvent.Reset -> {
                state = state.copy(
                    whiteMove = false,
                    blackMove = false,
                    whiteMoved = 0,
                    blackMoved = 0,
                    whiteSectionWeight = 1f,
                    blackSectionWeight = 1f,
                    isWhiteTimerStarted = false,
                    isBlackTimerStarted = false,
                    isBlackTimerPaused = false,
                    isWhiteTimerPaused = false,
                    isMatchStarted = false,
                    whiteTime = state.initialTimerValue,
                    blackTime = state.initialTimerValue,
                    //reset the timers here
                )
                stopTimer()
            }

            HomeEvent.MatchStarted -> {
                state = state.copy(isMatchStarted = true)
            }

            HomeEvent.PauseMatch -> {
                state = state.copy(isGeneralPause = true)
                pauseWhiteTimer()
                pauseBlackTimer()
            }

        }
    }




}