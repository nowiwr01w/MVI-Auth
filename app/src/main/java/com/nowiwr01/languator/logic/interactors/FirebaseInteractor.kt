package com.nowiwr01.languator.logic.interactors

import com.google.firebase.auth.FirebaseUser
import com.nowiwr01.languator.base.Result
import com.nowiwr01.languator.base.ResultRemote
import com.nowiwr01.languator.logic.errors.SignUpError
import com.nowiwr01.languator.logic.repository.FirebaseRepository

class FirebaseInteractor(
    private val firebaseRepository: FirebaseRepository
) {

    suspend fun signUp(email: String, password: String): Result<FirebaseUser?, SignUpError> {
        val user = firebaseRepository.createUser(email, password)
        return if (user.status == ResultRemote.Status.SUCCESS) {
            Result.Success(user.data)
        } else {
            Result.Fail(SignUpError.CreateUserError)
        }
    }
}