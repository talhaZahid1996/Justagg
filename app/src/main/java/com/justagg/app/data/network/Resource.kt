package com.justagg.app.data.network

import com.justagg.app.data.response.GeneralError

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: GeneralError?,
        val throwable: String? = ""
    ) : Resource<Nothing>()
}
