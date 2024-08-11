package com.bitmask.android.compose

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@SuppressLint("RememberReturnType")
@Composable
fun ClickCounter() {
    var counter by remember { mutableIntStateOf(0) }

    var pulseRateMs by remember { mutableStateOf(3000L) }
    val alpha = remember { Animatable(1f) }
    LaunchedEffect(pulseRateMs) { // Restart the effect when the pulse rate changes
        while (isActive) {
            delay(pulseRateMs) // Pulse the alpha every pulseRateMs to alert the user
            alpha.animateTo(0f)
            alpha.animateTo(1f)
        }
    }

    val scope = rememberCoroutineScope();

    Column(
        modifier = Modifier
            .padding(16.dp)
            .wrapContentSize(Alignment.Center)
    ) {
        Button(onClick = {
            counter++
            scope.launch {
                println("Button Clicked $counter times")
            }
        }) {
            Text("I've been clicked $counter times")
        }
    }
}