package com.huyhieu.coffee_go.ui.modifer.effect

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer

fun Modifier.rotateInfinite(duration: Int = 2000, easing: Easing = FastOutSlowInEasing) = composed {
    val infiniteTransition = rememberInfiniteTransition("RotateInfinite")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0F, targetValue = 360F, animationSpec = infiniteRepeatable(
            animation = tween(duration, easing = easing)
        ), label = "AngleFloat"
    )
    graphicsLayer {
        rotationZ = angle
    }
}