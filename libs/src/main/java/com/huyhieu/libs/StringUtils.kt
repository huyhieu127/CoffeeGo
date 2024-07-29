package com.huyhieu.libs

fun String?.append(content: String) = this?.let { "$this$content" } ?: content