package com.android.goally.data.util

sealed class ResultHandler<out T> {
    data class Success<out T>(val data: T) : ResultHandler<T>()
    data class Error(val exception: String) : ResultHandler<Nothing>()
}