package com.nowiwr01.languator.logic.repository.impl

import com.nowiwr01.languator.domain.UserData
import com.nowiwr01.languator.extensions.hasUpperChar
import com.nowiwr01.languator.extensions.isLongPassword
import com.nowiwr01.languator.extensions.isValidEmail
import com.nowiwr01.languator.logic.errors.SignUpTextError
import com.nowiwr01.languator.logic.repository.UserDataRepository

class UserDataRepositoryImpl: UserDataRepository {

    override fun isSignUpDataValid(userData: UserData): SignUpTextError? {
        val emptyFieldsList = mutableListOf<Int>()

        if (userData.email.isEmpty()) emptyFieldsList.add(1)
        if (userData.userName.isEmpty()) emptyFieldsList.add(2)
        if (userData.password.isEmpty()) emptyFieldsList.add(3)
        if (userData.passwordRepeated.isEmpty()) emptyFieldsList.add(4)

        if (emptyFieldsList.isNotEmpty()) {
            return SignUpTextError.createEmptyFieldMessage(emptyFieldsList)
        }
        if (!userData.email.isValidEmail()) {
            return SignUpTextError.createInvalidEmailMessage()
        }
        if (userData.password != userData.passwordRepeated) {
            return SignUpTextError.createNotEqualPasswordMessage()
        }
        if (!userData.password.isLongPassword()) {
            return SignUpTextError.createShortPasswordMessage()
        }
        if (!userData.password.hasUpperChar()) {
            return SignUpTextError.createWeakPasswordMessage()
        }

        return null
    }
}