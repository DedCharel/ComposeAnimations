package ru.nvgsoft.composeanimations.ui.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun AnimateContent() {

    var isFirstScreenLaunched by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { isFirstScreenLaunched = !isFirstScreenLaunched }
        ) {
            Text(text = "Switch screens")
        }

        AnimatedContent (targetState = isFirstScreenLaunched,
            //1)******************************************************
//            transitionSpec = {
//                (fadeIn(tween(2000)) +
//                        scaleIn()).togetherWith(fadeOut(tween(2000)))
//            }
            //2)******************************************************
//            transitionSpec = {
//                (slideIn(tween(2000)) { IntOffset(x = 100, y = 100) } +
//                        scaleIn()).togetherWith(fadeOut(tween(2000)))
//            }
            //3)*******************************************************
            //смещение влево
//            transitionSpec = {
//                (slideIn(tween(2000)) { IntOffset(x = it.width, y = 0) } +
//                        scaleIn()).togetherWith(fadeOut(tween(2000)))
//            }
            //4)*******************************************************
//            transitionSpec = {
//                (slideIn(tween(2000)) { IntOffset(x = 0, y = -it.height) } +
//                        scaleIn())
//                    .togetherWith(slideOut(tween(2000))
//                    { IntOffset(x = 0, y = -it.height) })
//            }
            //5)******************************************************
//            transitionSpec = {
//                (slideInHorizontally(tween(2000)) { -it } +
//                        scaleIn())
//                    .togetherWith(slideOutHorizontally(tween(2000))
//                    { it })
//            }
            //6)*******************************************************
            transitionSpec = {
                if (targetState){
                    (slideInHorizontally(tween(2000)) { -it } +
                            scaleIn())
                        .togetherWith(slideOutHorizontally(tween(2000))
                        { it })
                } else{
                    (slideInHorizontally(tween(2000)) { it } +
                            scaleIn())
                        .togetherWith(slideOutHorizontally(tween(2000))
                        { -it })
                }
            }

        ) {shouldLaunchFirstScreen ->
            if (shouldLaunchFirstScreen) {
                Screen1()
            } else {
                Screen2()
            }
        }


        if (isFirstScreenLaunched) {
            Screen1()
        } else {
            Screen2()
        }
    }
}

@Composable
private fun Screen1() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    )
}

@Composable
private fun Screen2() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    )
}