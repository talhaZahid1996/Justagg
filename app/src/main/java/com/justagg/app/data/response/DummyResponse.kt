package com.justagg.app.data.response

import com.justagg.app.R

data class DummyResponse(
    var name: String = "",
    var icon: Int = R.drawable.ic_dummy_social,
    var date: String = "Friday, 4/28",
    var designation: String = "CEO",
    var status: Boolean = false,
)
