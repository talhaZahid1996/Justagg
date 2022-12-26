package com.justagg.app.util

interface GeneralListener {
    fun buttonClick(clicked: Boolean) {}
    fun bottomSheetListener(source: String, value: String?) {}
    fun pickDate(value: String) {}
}