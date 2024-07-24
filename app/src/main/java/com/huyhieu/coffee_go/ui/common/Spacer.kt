package com.huyhieu.coffee_go.ui.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.huyhieu.coffee_go.ui.theme.edgeSize

@Composable
fun SpacerVertical(height: Dp = edgeSize) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun SpacerHorizontal(height: Dp = edgeSize) {
    Spacer(modifier = Modifier.width(height))
}