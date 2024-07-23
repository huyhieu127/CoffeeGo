package com.huyhieu.coffee_go.ui.common.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.huyhieu.coffee_go.ui.theme.Line
import com.huyhieu.coffee_go.ui.theme.Primary
import com.huyhieu.coffee_go.ui.theme.PrimaryLight
import com.huyhieu.coffee_go.ui.theme.utils.brush.BrushStyle

fun Modifier.gradientStyle(
    shape: Shape,
    brush: Brush = BrushStyle.GradientPrimary_Horizontal,
    elevation: Dp = 12.dp
) = this
    .shadow(
        elevation = elevation,
        shape = shape,
        spotColor = Primary,
        ambientColor = Primary,
    )
    .background(
        shape = shape,
        brush = brush,
    )
    .clip(shape)

fun Modifier.lightStyle(
    shape: Shape,
    elevation: Dp = 0.dp
) = this
    .shadow(
        elevation = elevation,
        shape = shape,
        spotColor = Primary,
        ambientColor = Primary,
    )
    .background(
        color = PrimaryLight,
        shape = shape,
    )
    .clip(shape)

fun Modifier.outlineStyle(
    shape: Shape,
    elevation: Dp = 0.dp
) = this
    .shadow(
        elevation = elevation,
        shape = shape,
        spotColor = Primary,
        ambientColor = Primary,
    )
    .border(
        color = Line,
        width = 1.dp,
        shape = shape,
    )
    .background(
        color = Color.White,
        shape = shape,
    )
    .clip(shape)

fun Modifier.clickableNoneRipple(onClick: () -> Unit): Modifier = composed {
    val interactionSource = remember { MutableInteractionSource() }
    this.clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = onClick
    )
}