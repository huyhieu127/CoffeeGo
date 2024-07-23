package com.huyhieu.listentogether.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.huyhieu.coffee_go.R
import com.huyhieu.coffee_go.ui.common.button.gradientStyle

@Preview
@Composable
fun LogoApp(
    modifier: Modifier = Modifier,
    padding: Dp = 12.dp,
    drawableRes: Int = R.drawable.ic_headset
) {
    Icon(
        painter = painterResource(id = drawableRes),
        contentDescription = null,
        modifier = modifier
            .gradientStyle(CircleShape)
            .padding(padding),
        tint = Color.White
    )
}