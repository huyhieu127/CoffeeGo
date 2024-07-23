package com.huyhieu.coffee_go.ui.common.otp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.huyhieu.coffee_go.ui.theme.Gray
import com.huyhieu.coffee_go.ui.theme.Line
import com.huyhieu.coffee_go.ui.theme.PrimaryLight
import com.huyhieu.coffee_go.ui.theme.utils.brush.BrushStyle

fun Modifier.boxOtp(isActive: Boolean = false) =
    if (isActive) this.boxOtpActive() else this.boxOtp()

fun Modifier.boxOtp() = this
    .background(
        shape = RoundedCornerShape(14.dp),
        color = Gray,
    )
    .border(
        width = 1.dp,
        color = Line,
        shape = RoundedCornerShape(14.dp),
    )

fun Modifier.boxOtpActive() = this
    .background(
        shape = RoundedCornerShape(14.dp),
        color = PrimaryLight,
    )
    .border(
        width = 1.dp,
        brush = BrushStyle.GradientPrimary_Horizontal,
        shape = RoundedCornerShape(14.dp),
    )
