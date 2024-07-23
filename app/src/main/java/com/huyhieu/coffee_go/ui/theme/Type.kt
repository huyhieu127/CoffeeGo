package com.huyhieu.coffee_go.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.huyhieu.coffee_go.ui.theme.utils.type.AppFontFamily

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val fontFamilyDefault get() = AppFontFamily.josefinSans

// Set of Material typography styles to start with
val TypographyDefault = Typography()
val AppTypography = Typography(
    displayLarge = TypographyDefault.displayLarge.copy(
        fontFamily = fontFamilyDefault
    ),
    displayMedium = TypographyDefault.displayMedium.copy(
        fontFamily = fontFamilyDefault
    ),
    displaySmall = TypographyDefault.displaySmall.copy(
        fontFamily = fontFamilyDefault
    ),
    headlineLarge = TypographyDefault.headlineLarge.copy(
        fontFamily = fontFamilyDefault
    ),
    headlineMedium = TypographyDefault.headlineMedium.copy(
        fontFamily = fontFamilyDefault
    ),
    headlineSmall = TypographyDefault.headlineSmall.copy(
        fontFamily = fontFamilyDefault
    ),
    titleLarge = TypographyDefault.titleLarge.copy(
        fontFamily = fontFamilyDefault
    ),
    titleMedium = TypographyDefault.titleMedium.copy(
        fontFamily = fontFamilyDefault
    ),
    titleSmall = TypographyDefault.titleSmall.copy(
        fontFamily = fontFamilyDefault
    ),
    bodyLarge = TypographyDefault.bodyLarge.copy(
        fontFamily = fontFamilyDefault,
    ),
    bodyMedium = TypographyDefault.bodyMedium.copy(
        fontFamily = fontFamilyDefault
    ),
    bodySmall = TypographyDefault.bodySmall.copy(
        fontFamily = fontFamilyDefault
    ),
    labelLarge = TypographyDefault.labelLarge.copy(
        fontFamily = fontFamilyDefault
    ),
    labelMedium = TypographyDefault.labelMedium.copy(
        fontFamily = fontFamilyDefault
    ),
    labelSmall = TypographyDefault.labelSmall.copy(
        fontFamily = fontFamilyDefault
    ),
)