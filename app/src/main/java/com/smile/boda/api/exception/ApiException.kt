package com.smile.boda.api.exception

import com.past3.ketro.api.ApiErrorHandler
import retrofit2.Response

class ApiException : ApiErrorHandler(){
    override fun getExceptionType(response: Response<*>): Exception {
        return when (response.code()) {
            LOGIN_ERROR -> LoginException()
            else -> Exception("Unknown error")
        }
    }

    companion object ErrorConfig {
        const val LOGIN_ERROR = 401

        class LoginException : Exception() {
            override val message = "Error unauthorised user"
        }
    }
}