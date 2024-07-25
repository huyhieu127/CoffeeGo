package com.huyhieu.coffee_go.ui.theme.utils.type

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

/**
 * Short styles
 * */
fun TextStyle.size(fontSize: TextUnit) =
    copy(fontSize = fontSize, lineHeight = fontSize)

fun TextStyle.textAlign(textAlign: TextAlign) = copy(textAlign = textAlign)

fun TextStyle.brush(brush: Brush) = copy(brush = brush)
