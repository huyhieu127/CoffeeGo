package com.huyhieu.coffee_go.uitls

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.drawable.toBitmap
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.huyhieu.coffee_go.ui.theme.PrimaryLight
import com.huyhieu.libs.PaletteType
import com.huyhieu.libs.measureColors
import kotlinx.coroutines.Dispatchers

@Composable
fun imageRequestUrl(
    url: String,
    onColorPalette: (List<Color>) -> Unit = {},
) = imageRequestUrl(url)
    .listener { _, result ->
        val bitmap = result.drawable.toBitmap(
            config = Bitmap.Config.ARGB_8888,
        )
        onColorPalette(bitmap.measureColors(PaletteType.Muted, PrimaryLight))
    }

@Composable
fun imageRequestUrl(
    url: String,
) = ImageRequest.Builder(localContext)
    .data(url)
    .allowHardware(false)
    .dispatcher(Dispatchers.IO)
    .memoryCacheKey(url)
    .diskCacheKey(url)
    .diskCachePolicy(CachePolicy.ENABLED)
    .memoryCachePolicy(CachePolicy.ENABLED)
    .size(coil.size.Size.ORIGINAL) // Set the target size to load the image at.
