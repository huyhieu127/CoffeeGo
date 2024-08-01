package com.huyhieu.domain.common

sealed class ResultState<T> {
    class Loading<T> : ResultState<T>()
    class Success<T>(val data: T) : ResultState<T>()
    class Error<T>(val error: String) : ResultState<T>()
    //class Complete<T> : ResourceState<T>()
}

fun <T> ResultState<T>.findData() = (this as? ResultState.Success)?.data