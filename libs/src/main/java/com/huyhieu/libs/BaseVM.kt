package com.huyhieu.libs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

open class BaseVM : ViewModel() {

    fun <T> Flow<T>.collectLatestData(onResult: ((T) -> Unit) = {}) = viewModelScope.launch {
        this@collectLatestData.collectLatest {
            onResult(it)
        }
    }
}