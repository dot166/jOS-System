package com.j.jOS.info.components

/**
 * Created by sds100 on 26/02/2020.
 */

/**
 * Inspired from @antonyharfield great example!
 */

sealed class Result<out T>

data class Success<T>(val value: T) : Result<T>()

sealed class Error : Result<Nothing>() {
    object NoAppToOpenUrl : Error()
}

fun <T> T.success() = Success(this)