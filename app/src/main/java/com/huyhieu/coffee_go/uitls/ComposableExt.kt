package com.huyhieu.coffee_go.uitls

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode

inline val localContext: Context
    @Composable
    get() = LocalContext.current

inline val localInspectionMode: Boolean
    @Composable
    get() = LocalInspectionMode.current