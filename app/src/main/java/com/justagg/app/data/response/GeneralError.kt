package com.justagg.app.data.response

import com.google.gson.annotations.SerializedName

data class GeneralError(

    @field:SerializedName("response")
    val response: String = "",

    @field:SerializedName("success")
    val success: Boolean? = false,

    @field:SerializedName("message")
    val message: String? = "Error"

)
