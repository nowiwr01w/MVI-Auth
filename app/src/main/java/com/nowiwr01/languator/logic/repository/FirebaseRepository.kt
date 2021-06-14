package com.nowiwr01.languator.logic.repository

import com.google.firebase.auth.FirebaseUser
import com.nowiwr01.languator.base.ResultRemote

interface FirebaseRepository {
    suspend fun loginUser(email: String, password: String): ResultRemote<FirebaseUser?>
    suspend fun createUser(email: String, password: String): ResultRemote<FirebaseUser?>
    suspend fun logout()
}