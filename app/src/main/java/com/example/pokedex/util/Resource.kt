package com.example.pokedex.util

sealed class Resource<T>(data: T? = null, message: String? = null) {

    class Success<T>(val data: T) : Resource<T>(data = data)
    class Error<T>(val message: String, val data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}
