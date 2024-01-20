package com.ahk.newsapp.base.domain

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ahk.newsapp.R

sealed class CustomException(
    open val data: CustomExceptionData,
) : Exception() {
    data class DatabaseException(override val data: CustomExceptionData) : CustomException(data)
    data class NetworkException(override val data: CustomExceptionData) : CustomException(data)
    data class InternetConnectionException(override val data: CustomExceptionData) :
        CustomException(data)

    data class UnknownException(override val data: CustomExceptionData) : CustomException(data)
    data class AuthenticationException(override val data: CustomExceptionData) :
        CustomException(data)

    data class ServerException(override val data: CustomExceptionData) : CustomException(data)
    data class ThereIsNoSuitableVariableException(override val data: CustomExceptionData) :
        CustomException(data)

    data class NotValidParametersException(override val data: CustomExceptionData) :
        CustomException(data)

    data class NoInternetException(override val data: CustomExceptionData) : CustomException(data)
}

data class CustomExceptionData(
    @StringRes val title: Int = R.string.Unknown_Error,
    @StringRes val message: Int = R.string.An_error_occurred_please_try_again_later,
    val code: Int = 202,
    @DrawableRes val image: Int = R.drawable.default_error_icon,
)

enum class CustomExceptionCode(val code: Int) {
    DATABASE_EXCEPTION(101),
    UNKNOWN_EXCEPTION(102),
    NOT_VALID_PARAMETERS_EXCEPTION(103),
    NO_INTERNET_EXCEPTION(400),
    AUTHENTICATION_EXCEPTION(401),
    REQUEST_TIMEOUT(408),
    TOO_MANY_REQUESTS(429),
    NOT_FOUND(404),
    SERVER_EXCEPTION(500),
    UNKNOWN_ERROR(-1),
}
