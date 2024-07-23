package com.huyhieu.domain.entity.response

sealed class ResponseState<T> {
    class Loading<T> : ResponseState<T>()
    class Success<T>(val data: T) : ResponseState<T>()
    class Error<T>(val error: String) : ResponseState<T>()
    //class Complete<T> : ResourceState<T>()
}