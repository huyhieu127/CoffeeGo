package com.huyhieu.coffee_go.ui.modifer.others

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import com.huyhieu.coffee_go.ui.theme.utils.brush.BrushStyle

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