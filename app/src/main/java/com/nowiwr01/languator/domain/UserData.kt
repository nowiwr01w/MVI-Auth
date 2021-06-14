package com.nowiwr01.languator.domain

data class UserData(
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
        ) = UserData(email, userName, password, passwordRepeated)
    }
}