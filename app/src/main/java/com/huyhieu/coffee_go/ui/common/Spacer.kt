package com.huyhieu.coffee_go.ui.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SpacerVertical(height: Dp = 20.dp) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun SpacerHorizontal(height: Dp = 20.dp) {
    Spacer(modifier = Modifier.width(height))
}