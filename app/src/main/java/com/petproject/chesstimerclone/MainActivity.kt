package com.petproject.chesstimerclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.petproject.chesstimerclone.presentation.screens.DarkSide
import com.petproject.chesstimerclone.presentation.screens.HomeScreen
import com.petproject.chesstimerclone.presentation.screens.WhiteSide
import com.petproject.chesstimerclone.ui.theme.ChessTimerCloneTheme
import com.petproject.chesstimerclone.ui.theme.primaryPink
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val systemUIController = rememberSystemUiController()

            val scope = rememberCoroutineScope()

            ChessTimerCloneTheme {

                systemUIController.setNavigationBarColor(
                    color = primaryPink
                )
                systemUIController.isStatusBarVisible = false

                LaunchedEffect(key1 = systemUIController.isStatusBarVisible){
                    if(systemUIController.isStatusBarVisible){
                        scope.launch {
                            delay(2000)
                            systemUIController.isStatusBarVisible = false
                        }
                    }
                }


                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(padding = innerPadding)
                }

            }
        }
    }
}
