package com.banregio.devuapp.util

sealed class DevUResponse<T> {

    data class Successful<T>(val content: T) : DevUResponse<T>()
    data class Failed<T>(val errorMessage: String) : DevUResponse<T>()

}