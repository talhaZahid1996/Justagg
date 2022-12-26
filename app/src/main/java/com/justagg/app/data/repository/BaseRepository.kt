package com.justagg.app.data.repository

import com.justagg.app.data.network.BaseApi
import com.justagg.app.data.network.SafeApiCall

abstract class BaseRepository(private val api: BaseApi) : SafeApiCall {

    suspend fun logout() = safeApiCall {
        api.logout()
    }

}