package com.huyhieu.coffee_go.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.huyhieu.coffee_go.ui.rotateInfinite

@Preview
@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Icon(
            Icons.Default.Refresh,
            contentDescription = null,
            tint = Color.Magenta,
            modifier = Modifier.rotateInfinite()
        )
    }
}


