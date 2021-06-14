package com.nowiwr01.languator.logic.repository

import com.nowiwr01.languator.domain.UserData
import com.nowiwr01.languator.logic.errors.SignUpTextError

interface UserDataRepository {
    fun isSignUpDataValid(userData: UserData): SignUpTextError?
}