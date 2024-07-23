package com.huyhieu.libs

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

const val TAG_GSON_UTILS = "GsonUtils"

inline fun <reified T> String?.toData(): T? {
    this ?: return null
    return runCatching<T> {
        val type = object : TypeToken<T>() {}.type
        Gson().fromJson(this@toData, type)
    }.getOrElse {
        logError("toData: ${it.message}", TAG_GSON_UTILS)
        null
    }
}

inline fun <reified T> String?.toMutableListData(): MutableList<T>? {
    this ?: return null
    return runCatching<MutableList<T>> {
        val type = object : TypeToken<MutableList<T>>() {}.type
        Gson().fromJson(this, type)
    }.getOrElse {
        logError("toMutableListData: ${it.message}", TAG_GSON_UTILS)
        mutableListOf()
    }
}

fun Any?.toJson(isPretty: Boolean = false): String {
    this ?: return ""
    return runCatching {
        val gson = if (isPretty) GsonBuilder().setPrettyPrinting().create() else Gson()
        gson.toJson(this) ?: ""
    }.getOrElse {
        logError("toJson: ${it.message}", TAG_GSON_UTILS)
        ""
    }
}