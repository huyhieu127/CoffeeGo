package com.huyhieu.coffee_go.ui

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.huyhieu.coffee_go.ui.theme.Line
import com.huyhieu.coffee_go.ui.theme.Primary
import com.huyhieu.coffee_go.ui.theme.PrimaryLight
import com.huyhieu.coffee_go.ui.theme.utils.brush.BrushStyle

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

fun Modifier.tintSelectStyle(
    isSelect: Boolean = false,
    brush: Brush = BrushStyle.GradientPrimary_Horizontal,
) = then(Modifier.takeIf { !isSelect } ?: Modifier
    .graphicsLayer(alpha = 0.99f)
    .drawWithCache {
        onDrawWithContent {
            drawContent()
            drawRect(brush = brush, blendMode = BlendMode.SrcAtop)
        }
    })

fun Modifier.tintGradientStyle(
    brush: Brush = BrushStyle.GradientPrimary_Horizontal,
) = this
    .graphicsLayer(alpha = 0.99f)
    .drawWithCache {
        onDrawWithContent {
            drawContent()
            drawRect(brush = brush, blendMode = BlendMode.SrcAtop)
        }
    }

fun Modifier.borderSelectStyle(isSelect: Boolean = false, width: Dp = 1.dp) =
    then(
        Modifier
            .borderGradient(width = width)
            .takeIf { isSelect } ?: Modifier.borderColor())

fun Modifier.borderGradient(
    brush: Brush = BrushStyle.GradientPrimary_Horizontal,
    width: Dp = 1.dp,
    shape: Shape = RoundedCornerShape(8.dp)
) = this.border(
    width = width, brush = brush, shape = shape
)

fun Modifier.borderColor(
    color: Color = Line, width: Dp = 1.dp, shape: Shape = RoundedCornerShape(8.dp)
) = this.border(
    width = width, color = color, shape = shape
)

fun Modifier.gradientStyle(
    shape: Shape,
    brush: Brush = BrushStyle.GradientPrimary_Horizontal,
    elevation: Dp = 8.dp,
    shadowColor: Color = Primary,
) = this
    .shadow(
        elevation = elevation,
        shape = shape,
        spotColor = shadowColor,
        ambientColor = shadowColor,
    )
    .background(
        shape = shape,
        brush = brush,
    )
    .clip(shape)

fun Modifier.lightStyle(
    shape: Shape, elevation: Dp = 0.dp
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
    shape: Shape, elevation: Dp = 0.dp
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
        interactionSource = interactionSource, indication = null, onClick = onClick
    )
}