package com.petproject.chesstimerclone.presentation.screens

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.petproject.chesstimerclone.R
import com.petproject.chesstimerclone.presentation.presenter.HomeEvent
import com.petproject.chesstimerclone.presentation.presenter.HomePresenter
import com.petproject.chesstimerclone.presentation.utils.NoRippleInteractionSource
import com.petproject.chesstimerclone.ui.theme.primaryBlue
import com.petproject.chesstimerclone.ui.theme.primaryPink
import kotlin.math.log

@Composable
fun HomeScreen(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    presenter: HomePresenter = viewModel()
) {

    val density = LocalDensity.current

    val configuration = LocalConfiguration.current


    Box(
        modifier = modifier
            .padding(padding)
            /*.onGloballyPositioned {
                presenter.screenSize = with(density) {
                    it.size.height.toDp()
                }
            }*/

    ) {
        Column(modifier = modifier.fillMaxSize()) {

            DarkSide {
                if (!presenter.state.blackMove) {
                    presenter.onEvent(event = HomeEvent.BlackMoved)
                }
                if(presenter.state.isMatchStarted.not()){
                    presenter.onEvent(event = HomeEvent.MatchStarted)
                }

            }
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                Column {
                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f, true)
                            .background(primaryBlue)
                    )
                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f, true)
                            .background(primaryPink)
                    )
                }

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .clip(CircleShape),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if(presenter.state.isMatchStarted.not()){
                        Box(
                            modifier = modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.6f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                                    .background(Color.White),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = modifier.size(27.dp),
                                    painter = painterResource(id = R.drawable.ic_settings),
                                    contentDescription = null
                                )
                            }
                        }
                    }

                    Box(
                        modifier = modifier
                            .size(74.dp)
                            .clip(CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        if(presenter.state.isMatchStarted){
                            Box(
                                modifier = modifier
                                    .size(74.dp)
                                    .clip(CircleShape)
                                    .background(Color.White.copy(alpha = 0.6f))
                                    .clickable(
                                        interactionSource = NoRippleInteractionSource(),
                                        indication = null
                                    ) {

                                        if(!presenter.state.isGeneralPause){
                                            presenter.onEvent(event = HomeEvent.PauseMatch)
                                        } else {
                                            presenter.onEvent(event = HomeEvent.Reset)
                                            Log.d("reset", "HomeScreen: Clicked")
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {

                                Box(
                                    modifier = modifier
                                        .size(60.dp)
                                        .clip(CircleShape)
                                        .background(Color.White),
                                    contentAlignment = Alignment.Center
                                ) {

                                    //if isStarted is true, display pause, if is pause is true, display start
                                    Icon(
                                        modifier = modifier.size(40.dp),
                                        painter = painterResource(
                                            id =

                                            if(presenter.state.isGeneralPause){
                                                R.drawable.ic_reset
                                            }else{
                                                R.drawable.ic_pause
                                            }


                                        ), contentDescription = null, tint = primaryBlue
                                    )

                                }
                            }
                        }

                    }
                    if(presenter.state.isMatchStarted.not()){
                        Box(
                            modifier = modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.6f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                                    .background(Color.White),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = modifier.size(27.dp),
                                    painter = painterResource(id = R.drawable.ic_palette),
                                    contentDescription = null,
                                    tint = primaryBlue
                                )
                            }
                        }
                    }

                }
            }
            WhiteSide(
                modifier = modifier

            ) {
                if(!presenter.state.whiteMove) {
                    presenter.onEvent(event = HomeEvent.WhiteMoved)
                }
                if(presenter.state.isMatchStarted.not()){
                    presenter.onEvent(event = HomeEvent.MatchStarted)
                }


            }


        }
        /* Box(modifier = modifier
             .fillMaxSize()
             ,
             contentAlignment = Alignment.Center){


         }*/


    }

}