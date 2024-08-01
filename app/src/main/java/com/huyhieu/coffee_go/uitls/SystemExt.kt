package com.huyhieu.coffee_go.uitls

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable

val heightStatusBar
    @Composable
    get() = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()