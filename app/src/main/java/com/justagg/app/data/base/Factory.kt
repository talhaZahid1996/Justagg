package com.justagg.app.data.base

interface Factory<T> {
    fun create() : T
}