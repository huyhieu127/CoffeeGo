package com.huyhieu.coffee_go.ui.theme.utils.type

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import com.huyhieu.coffee_go.ui.theme.fontFamilyDefault

object FontStyle {
    private val fontSizeDefault = 16.sp
    private const val ratioLineHeightDefault = 1.4F

    val Light = TextStyle(
        fontFamily = fontFamilyDefault,
        fontWeight = FontWeight.Light,
        fontSize = fontSizeDefault,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.LastLineBottom,
        )
    )

    val Regular = TextStyle(
        fontFamily = fontFamilyDefault,
        fontWeight = FontWeight.Normal,
        fontSize = fontSizeDefault,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.LastLineBottom,
        )
    )

    val Medium = TextStyle(
        fontFamily = fontFamilyDefault,
        fontWeight = FontWeight.Medium,
        fontSize = fontSizeDefault,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.LastLineBottom,
        )
    )

    val SemiBold = TextStyle(
        fontFamily = fontFamilyDefault,
        fontWeight = FontWeight.SemiBold,
        fontSize = fontSizeDefault,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.LastLineBottom,
        )
    )

    val Bold = TextStyle(
        fontFamily = fontFamilyDefault,
        fontWeight = FontWeight.Bold,
        fontSize = fontSizeDefault,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.LastLineBottom,
        )
    )
}
