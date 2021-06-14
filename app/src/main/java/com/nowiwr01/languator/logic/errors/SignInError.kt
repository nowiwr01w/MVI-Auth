package com.nowiwr01.languator.logic.errors

sealed class SignInError {
    object LoginUserError: SignInError()
}
