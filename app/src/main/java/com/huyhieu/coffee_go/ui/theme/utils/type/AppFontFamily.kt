package com.huyhieu.coffee_go.ui.theme.utils.type

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.huyhieu.coffee_go.R

object AppFontFamily {
    val josefinSans by lazy {
        FontFamily(
            Font(R.font.josefin_sans_thin, FontWeight.Thin, FontStyle.Normal),
            Font(R.font.josefin_sans_thin_italic, FontWeight.Thin, FontStyle.Italic),
            Font(R.font.josefin_sans_extra_light, FontWeight.ExtraLight, FontStyle.Normal),
            Font(R.font.josefin_sans_extra_light_italic, FontWeight.ExtraLight, FontStyle.Italic),
            Font(R.font.josefin_sans_light, FontWeight.Light, FontStyle.Normal),
            Font(R.font.josefin_sans_light_italic, FontWeight.Light, FontStyle.Italic),
            Font(R.font.josefin_sans_regular, FontWeight.Normal, FontStyle.Normal),
            Font(R.font.josefin_sans_italic, FontWeight.Normal, FontStyle.Italic),
            Font(R.font.josefin_sans_medium, FontWeight.Medium, FontStyle.Normal),
            Font(R.font.josefin_sans_medium_italic, FontWeight.Medium, FontStyle.Italic),
            Font(R.font.josefin_sans_semi_bold, FontWeight.SemiBold, FontStyle.Normal),
            Font(R.font.josefin_sans_semi_bold_italic, FontWeight.SemiBold, FontStyle.Italic),
            Font(R.font.josefin_sans_bold, FontWeight.Bold, FontStyle.Normal),
            Font(R.font.josefin_sans_bold, FontWeight.Bold, FontStyle.Italic),

            Font(
                R.font.josefin_sans_bold,
                FontWeight.ExtraBold,
                FontStyle.Normal
            ),//800 - Unavailable
            Font(
                R.font.josefin_sans_bold,
                FontWeight.ExtraBold,
                FontStyle.Italic
            ),//800 - Unavailable
            Font(R.font.josefin_sans_bold, FontWeight.Black, FontStyle.Normal),//900 - Unavailable
            Font(R.font.josefin_sans_bold, FontWeight.Black, FontStyle.Italic),//900 - Unavailable
        )
    }
}