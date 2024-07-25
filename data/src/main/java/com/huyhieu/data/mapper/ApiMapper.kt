package com.huyhieu.data.mapper

import com.huyhieu.domain.common.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import retrofit2.Response

object ApiMapper {
    fun <T> asFlowSimple(api: suspend () -> Response<T>) = flow<ResponseState<T>> {
        emit(ResponseState.Loading())
        val response = api.invoke()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            emit(ResponseState.Success(data = body))
        } else {
            emit(ResponseState.Error(error = response.message()))
        }
    }.catch {
        emit(ResponseState.Error(error = it.localizedMessage ?: "API: Something error!"))
    }


    fun <T> asFlow(
        api: suspend () -> Response<T>//, onEach: ((ResponseState<T>) -> Unit) = { }
    ) = flow<ResponseState<T>> {
        val response = api.invoke()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            emit(ResponseState.Success(data = body))
        } else {
            emit(ResponseState.Error(error = response.message()))
        }
    }.flowOn(Dispatchers.IO).onStart {
        emit(ResponseState.Loading())
    }.catch {
        emit(ResponseState.Error(error = it.localizedMessage ?: "API: Something error!"))
    }
//        .onCompletion {
//        emit(ResponseState.Complete())
//    }
//        .onEach {
//            onEach.invoke(it)
//        }

    fun <T, R> asFlow(
        onConverter: T.() -> R,
        api: suspend () -> Response<T>//, onEach: ((ResponseState<T>) -> Unit) = { }
    ) = flow {
        val response = api.invoke()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            emit(ResponseState.Success(data = onConverter(body)))
        } else {
            emit(ResponseState.Error(error = response.message()))
        }
    }.flowOn(Dispatchers.IO).onStart {
        emit(ResponseState.Loading())
    }.catch {
        emit(ResponseState.Error(error = it.localizedMessage ?: "API: Something error!"))
    }
}