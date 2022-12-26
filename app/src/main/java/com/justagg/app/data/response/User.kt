package com.justagg.app.data.response

import com.google.gson.annotations.SerializedName

data class User(
    @field:SerializedName("cus_email")
    val cusEmail: String? = null,

    @field:SerializedName("last_name")
    val lastName: String? = null,

    @field:SerializedName("first_name")
    val firstName: String? = null,

    @field:SerializedName("cus_id")
    val cusId: String? = null,

    @field:SerializedName("token")
    val token: String? = "",

    @field:SerializedName("cus_mobile")
    val cusMobile: String? = null
)
