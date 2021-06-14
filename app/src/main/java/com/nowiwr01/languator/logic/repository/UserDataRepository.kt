package com.nowiwr01.languator.logic.repository

import com.nowiwr01.languator.domain.UserDataSignIn
import com.nowiwr01.languator.domain.UserDataSignUp
import com.nowiwr01.languator.logic.errors.SignInTextError
import com.nowiwr01.languator.logic.errors.SignUpTextError

interface UserDataRepository {
    fun isSignUpDataValid(userData: UserDataSignUp): SignUpTextError?
    fun isSignInDataValid(userData: UserDataSignIn): SignInTextError?
}