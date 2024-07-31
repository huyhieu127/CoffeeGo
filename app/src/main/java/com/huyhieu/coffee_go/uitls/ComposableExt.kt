package com.huyhieu.coffee_go.uitls

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp

inline val localContext: Context
    @Composable
    get() = LocalContext.current

inline val localInspectionMode: Boolean
    @Composable
    get() = LocalInspectionMode.current

val localConfiguration
    @Composable
    get() = LocalConfiguration.current

val screenHeight
    @Composable
    get() = localConfiguration.screenHeightDp.dp
val screenWidth
    @Composable
    get() = localConfiguration.screenWidthDp.dp

@Composable
inline fun <T> T?.takeIfInspectionMode(otherCondition: (T.() -> Boolean) = { true }, onTakeDefault: () -> T): T =
    this?.takeIf { localInspectionMode && otherCondition.invoke(this)} ?: onTakeDefault()