package com.nowiwr01.languator.logic.repository.impl

import com.google.firebase.auth.FirebaseAuth
import com.nowiwr01.languator.base.ResultRemote
import com.nowiwr01.languator.logic.repository.FirebaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FirebaseRepositoryImpl(
    private val auth: FirebaseAuth,
): FirebaseRepository {

    override suspend fun loginUser(email: String, password: String) = withContext(Dispatchers.IO) {
        try {
            val response = auth.signInWithEmailAndPassword(email, password).await()
            ResultRemote.success(response.user)
        } catch (throwable: Throwable) {
            ResultRemote.error("Хуйня, а не авторизация")
        }
    }

    override suspend fun createUser(email: String, password: String) = withContext(Dispatchers.IO) {
        try {
            val response = auth.createUserWithEmailAndPassword(email, password).await()
            ResultRemote.success(response.user)
        } catch (throwable: Throwable) {
            ResultRemote.error("Хуйня, а не регистрация")
        }
    }

    override suspend fun logout() {

    }
}