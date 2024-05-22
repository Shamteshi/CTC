package com.petproject.chesstimerclone.presentation.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.petproject.chesstimerclone.presentation.utils.NoRippleInteractionSource
import com.petproject.chesstimerclone.presentation.presenter.HomePresenter
import com.petproject.chesstimerclone.presentation.utils.formatTime
import com.petproject.chesstimerclone.ui.theme.primaryPink

@Composable
fun ColumnScope.WhiteSide(
    modifier: Modifier = Modifier,
    background: Color = primaryPink,
    presenter: HomePresenter = viewModel(),
    onClick: () -> Unit
) {

    val whiteTimerValue by presenter.whiteTimer.collectAsState()


    val whiteSection by animateFloatAsState(targetValue = presenter.state.whiteSectionWeight,
        animationSpec = tween(
            durationMillis = 150,
            easing = LinearEasing
        ),
        label = "white_section"
    )

    Box(modifier = modifier
        .fillMaxSize()
        .animateContentSize()
        .weight(whiteSection, true)
        .background(background)
        .clickable(
            interactionSource = NoRippleInteractionSource(),
            indication = null,
        ) {
            onClick.invoke()
        }
    ) {
        Column(
            modifier = modifier
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = whiteTimerValue.formatTime(),
                style = TextStyle(
                    fontSize = 90.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),

            )
            if(presenter.state.isMatchStarted.not()){
                Text(
                    text = "Tap to start",
                    style = TextStyle(color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Medium)
                )
            }
            if(presenter.state.isWhiteTimerPaused && presenter.state.isGeneralPause){
                Text(
                    text = "Tap to resume",
                    style = TextStyle(color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Medium)
                )
            }
            /*Text(text = if(presenter.state.isMatchStarted.not()) "Tap to start" else "Tap to resume",
                style = TextStyle(color = Color.White, fontSize = 23.sp)
            )*/

        }
        Text(
            text = presenter.state.whiteMoved.toString(),
            style = TextStyle(
                fontSize = 30.sp,
                color = Color.White
            ),
            modifier = modifier
                .padding(20.dp)
                .align(Alignment.BottomEnd)
        )
    }
}