package com.huyhieu.coffee_go.ui.theme.utils.type

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

/**
 * Short styles
 * */
fun TextStyle.size(fontSize: TextUnit, ratioLineHeight: Float = 1.4F) =
    copy(fontSize = fontSize, lineHeight = measureRatioLineHeight(fontSize, ratioLineHeight))

fun TextStyle.textAlign(textAlign: TextAlign) = copy(textAlign = textAlign)

fun TextStyle.brush(brush: Brush) = copy(brush = brush)

/**
 * Handle styles
 * */
fun measureRatioLineHeight(fontSize: TextUnit, ratio: Float = 1.4F) = fontSize * ratio

fun TextStyle.ratioLineHeight(ratio: Float = 1.4F) =
    copy(lineHeight = measureRatioLineHeight(fontSize, ratio))
