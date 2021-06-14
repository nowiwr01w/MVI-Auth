package com.nowiwr01.languator.logic.errors

sealed class SignUpError {
    object CreateUserError: SignUpError()
}
