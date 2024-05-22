package com.petproject.chesstimerclone.presentation.utils

import androidx.compose.ui.text.intl.Locale

fun Long.formatTime(): String{
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val remainingSeconds = this % 60

    //using only minutes and remaining seconds for this one.

    return String.format("%02d:%02d", minutes, remainingSeconds)
}