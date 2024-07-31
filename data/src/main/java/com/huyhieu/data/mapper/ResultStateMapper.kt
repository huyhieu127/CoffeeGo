package com.huyhieu.data.mapper

import com.huyhieu.domain.common.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import retrofit2.Response

interface ResultStateMapperImpl : ResultStateDbMapper, ResultStateApiMapper

interface ResultStateDbMapper {
    fun <T> flowResultStateDB(dao: suspend () -> T): Flow<ResultState<T>> {
        return flow {
            emit(ResultState.Loading())
            runCatching {
                dao.invoke()
            }.onFailure {
                emit(ResultState.Error(it.localizedMessage ?: "An error occurred!"))
            }.onSuccess { result ->
                if (result is Number && result.toInt() > 0) {
                    emit(ResultState.Success(result))
                } else {
                    emit(ResultState.Error("Query failed!"))
                }
            }
        }
    }

    fun <T, R> Flow<T>.asResultStateDB(onConverter: T.() -> R) = channelFlow<ResultState<R>> {
        collectLatest { value ->
            trySend(ResultState.Success(data = onConverter(value)))
        }
    }.flowOn(Dispatchers.IO).onStart {
        emit(ResultState.Loading())
    }.catch {
        emit(ResultState.Error(error = it.localizedMessage ?: "API: Something error!"))
    }
}

interface ResultStateApiMapper {

    fun <T, R> asResultStateApi(
        onConverter: T.() -> R,
        api: suspend () -> Response<T>
    ) = flow {
        val response = api.invoke()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            emit(ResultState.Success(data = onConverter(body)))
        } else {
            emit(ResultState.Error(error = response.message()))
        }
    }.flowOn(Dispatchers.IO).onStart {
        emit(ResultState.Loading())
    }.catch {
        emit(ResultState.Error(error = it.localizedMessage ?: "API: Something error!"))
    }


    fun <T> asResultStateApi(
        api: suspend () -> Response<T>//, onEach: ((ResponseState<T>) -> Unit) = { }
    ) = flow<ResultState<T>> {
        val response = api.invoke()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            emit(ResultState.Success(data = body))
        } else {
            emit(ResultState.Error(error = response.message()))
        }
    }.flowOn(Dispatchers.IO).onStart {
        emit(ResultState.Loading())
    }.catch {
        emit(ResultState.Error(error = it.localizedMessage ?: "API: Something error!"))
    }
//        .onCompletion {
//        emit(ResponseState.Complete())
//    }
//        .onEach {
//            onEach.invoke(it)
//        }

    fun <T> asFlowSimple(api: suspend () -> Response<T>) = flow<ResultState<T>> {
        emit(ResultState.Loading())
        val response = api.invoke()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            emit(ResultState.Success(data = body))
        } else {
            emit(ResultState.Error(error = response.message()))
        }
    }.catch {
        emit(ResultState.Error(error = it.localizedMessage ?: "API: Something error!"))
    }

}