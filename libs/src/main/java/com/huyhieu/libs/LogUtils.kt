package com.huyhieu.libs

import android.util.Log

//Log
const val TAG_LOG = ">>>>>>>>>>>"

fun Any.className() = this::class.simpleName

/**
 * Array keyword block
 * */
private val keywordsBlock = arrayOf("NetworkConfigs")

/**
 * Block log when message contains keyword in array
 * */
private fun blockLogger(str: String?): Boolean? {
    keywordsBlock.forEach {
        if (str?.contains(it) == true) return null
    }
    return true
}

fun Any.logError(str: String? = "", tag: String? = className()) {
    blockLogger(str) ?: return
    runCatching {
        Log.e(tag, "$str")
    }
}

fun Any.logDebug(str: String? = "", tag: String? = className()) {
    blockLogger(str) ?: return
    runCatching {
        Log.d(tag, "$str")
    }
}

fun Any.logInfo(str: String? = "", tag: String? = className()) {
    blockLogger(str) ?: return
    runCatching {
        Log.i(tag, "$str")
    }
}