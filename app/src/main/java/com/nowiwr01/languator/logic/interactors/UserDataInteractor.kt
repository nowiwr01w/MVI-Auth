package com.nowiwr01.languator.logic.interactors

import com.nowiwr01.languator.domain.UserData
import com.nowiwr01.languator.logic.repository.UserDataRepository

class UserDataInteractor(
    private val userDataRepository: UserDataRepository
) {

    fun isUserInputValid(userData: UserData) = userDataRepository.isSignUpDataValid(userData)
}