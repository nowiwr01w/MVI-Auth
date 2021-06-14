package com.nowiwr01.languator.logic.interactors

import com.nowiwr01.languator.domain.UserDataSignIn
import com.nowiwr01.languator.domain.UserDataSignUp
import com.nowiwr01.languator.logic.repository.UserDataRepository

class UserDataInteractor(
    private val userDataRepository: UserDataRepository
) {

    fun isSignUpDataValid(userData: UserDataSignUp) = userDataRepository.isSignUpDataValid(userData)

    fun isSignInDataValid(userData: UserDataSignIn) = userDataRepository.isSignInDataValid(userData)
}