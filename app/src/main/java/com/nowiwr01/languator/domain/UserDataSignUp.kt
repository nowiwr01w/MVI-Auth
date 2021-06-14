package com.nowiwr01.languator.domain

data class UserDataSignUp(
    val email: String,
    val userName: String,
    val password: String,
    val passwordRepeated: String
) {
    companion object {
        fun createUserData(
            email: String,
            userName: String,
            password: String,
            passwordRepeated: String
        ) = UserDataSignUp(email, userName, password, passwordRepeated)
    }
}