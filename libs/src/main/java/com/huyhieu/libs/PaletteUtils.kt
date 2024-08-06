package com.huyhieu.libs

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette

enum class PaletteType {
    Dominant,
    Vibrant,
    Muted
}

fun Bitmap.toPaletteSyncColors(): Map<PaletteType, List<Color>> {
    toPaletteSync().let { palette ->
        //Dominant
        val color0 = palette.getDominantColor(0)
        //Vibrant
        val color1 = palette.getLightVibrantColor(0)
        val color2 = palette.getVibrantColor(0)
        val color3 = palette.getDarkVibrantColor(0)
        val listVibrant = listOf(color1, color2, color3).filter { it != 0 }.map { Color(it) }
        //Muted
        val color4 = palette.getLightMutedColor(0)
        val color5 = palette.getMutedColor(0)
        val color6 = palette.getDarkMutedColor(0)
        val listMuted = listOf(color4, color5, color6).filter { it != 0 }.map { Color(it) }

        return mapOf(
            PaletteType.Dominant to listOf(Color(color0)),
            PaletteType.Vibrant to listVibrant,
            PaletteType.Muted to listMuted,
        )
    }
}

fun Bitmap.measureColors(paletteType: PaletteType, colorsDefault: Color): List<Color> {
    this.toPaletteSyncColors().also { paletteTypeListMap ->
        val colors = paletteTypeListMap[paletteType]
        return when {
            colors.isNullOrEmpty() -> {
                val dominant =
                    paletteTypeListMap[PaletteType.Dominant].orEmpty()
                        .firstOrNull()
                if (dominant == null)
                    listOf(colorsDefault, colorsDefault)
                else
                    listOf(dominant, dominant)
            }

            colors.size == 1 -> {
                val dominant =
                    paletteTypeListMap[PaletteType.Dominant].orEmpty()
                        .firstOrNull()
                if (dominant == null) {
                    listOf(
                        colors.first(),
                        colors.first(),
                    )
                } else {
                    listOf(
                        colors.first(),
                        dominant,
                    )
                }
            }

            else -> colors

        }
    }
}

fun Bitmap.toPaletteSync(): Palette = Palette.from(this).generate()

fun Bitmap?.toPaletteAsync(onGenerate: (Palette) -> Unit) {
    if (this == null) return
    Palette.from(this).generate { palette ->
        palette?.let { onGenerate(it) }
    }
}