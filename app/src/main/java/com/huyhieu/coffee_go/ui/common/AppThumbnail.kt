package com.huyhieu.coffee_go.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.huyhieu.coffee_go.R
import com.huyhieu.coffee_go.ui.common.button.gradientStyle
import com.huyhieu.coffee_go.ui.theme.utils.brush.BrushStyle

@Preview
@Composable
fun AppThumbnail(
    modifier: Modifier = Modifier,
    drawableRes: Int = R.drawable.ic_done,
    brush: Brush = BrushStyle.GradientPrimary_Horizontal,
    padding: Dp = 100.dp
) {
    Icon(
        painter = painterResource(id = drawableRes),
        contentDescription = null,
        modifier = modifier
            .gradientStyle(
                shape = CircleShape,
                brush = brush,
            )
            .padding(padding),
        tint = Color.White
    )
}