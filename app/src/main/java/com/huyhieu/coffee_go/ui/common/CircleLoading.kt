package com.huyhieu.coffee_go.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.huyhieu.coffee_go.ui.theme.Primary
import com.huyhieu.coffee_go.ui.theme.PrimaryLight

@Preview
@Composable
fun CircleLoading(modifier: Modifier = Modifier, size: Dp = 42.dp) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            color = Primary,
            trackColor = PrimaryLight,
            strokeCap = StrokeCap.Round,
            strokeWidth = 5.dp,
            modifier = Modifier
                .size(size)
                .shadow(
                    elevation = 2.dp,
                    shape = CircleShape,
                    spotColor = Primary,
                    ambientColor = Primary,
                )
        )
        Box(
            modifier = Modifier
                .size(size)
                .padding(5.dp)
                .background(
                    color = Color.White,
                    shape = CircleShape,
                )
        )
    }
}