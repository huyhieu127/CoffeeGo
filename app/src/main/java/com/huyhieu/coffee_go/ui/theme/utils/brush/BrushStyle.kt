package com.huyhieu.coffee_go.ui.theme.utils.brush

import androidx.compose.ui.graphics.Brush

object BrushStyle {
    val GradientPrimary_Horizontal by lazy {
        Brush.horizontalGradient(
            colors = listOf(GradientPrimaryStart, GradientPrimaryEnd),
        )
    }
    val GradientInfo_Horizontal by lazy {
        Brush.horizontalGradient(
            colors = listOf(GradientInfoStart, GradientInfoEnd),
        )
    }
    val GradientError_Horizontal by lazy {
        Brush.horizontalGradient(
            colors = listOf(GradientErrorStart, GradientErrorEnd),
        )
    }
    val GradientWarning_Horizontal by lazy {
        Brush.horizontalGradient(
            colors = listOf(GradientWarningStart, GradientWarningEnd),
        )
    }
}