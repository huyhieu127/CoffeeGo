package com.huyhieu.libs

import android.icu.text.DecimalFormat


fun Double.formatDollar(): String {
    val pattern = "#." + "#".repeat(2)
    val decimalFormat = DecimalFormat(pattern)
    return decimalFormat.format(this)
}

fun Double.format(digits: Int): String {
    val pattern = "#." + "#".repeat(digits)
    val decimalFormat = DecimalFormat(pattern)
    return decimalFormat.format(this)
}