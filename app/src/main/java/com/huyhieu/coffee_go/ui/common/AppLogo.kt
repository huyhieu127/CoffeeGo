package com.huyhieu.coffee_go.ui.common

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.huyhieu.coffee_go.R
import com.huyhieu.coffee_go.ui.tintGradientStyle

@Preview
@Composable
fun AppLogo(
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    drawableRes: Int = R.drawable.ic_coffee_outline
) {
    Icon(
        painter = painterResource(id = drawableRes),
        contentDescription = null,
        modifier = modifier
            .tintGradientStyle()
            .size(size),
    )
}