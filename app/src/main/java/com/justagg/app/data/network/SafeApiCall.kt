package com.justagg.app.data.network

import android.accounts.NetworkErrorException
import android.util.Log
import com.google.gson.Gson
import com.justagg.app.data.response.GeneralError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.http.conn.ConnectTimeoutException
import retrofit2.HttpException

interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                Log.e("SafeApiCall", "safeApiCall: ${throwable.message}")
                when (throwable) {
                    is HttpException -> {
                        Resource.Failure(
                            false,
                            throwable.code(),
                            Gson().fromJson(
                                throwable.response()?.errorBody()?.charStream(),
                                GeneralError::class.java
                            ),
                            null
                        )
                    }
                    is NetworkErrorException -> {
                        Resource.Failure(true, null, null)
                    }
                    is ConnectTimeoutException -> {
                        Resource.Failure(true, 100, null, "TimeOut Exception Manual")
                    }
                    else -> {
                        Resource.Failure(false, throwable.hashCode(), null, throwable.message)
                    }
                }
            }
        }
    }
}