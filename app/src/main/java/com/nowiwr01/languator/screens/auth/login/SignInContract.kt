package com.nowiwr01.languator.screens.auth.login

import com.google.firebase.auth.FirebaseUser
import com.nowiwr01.languator.base.UiEffect
import com.nowiwr01.languator.base.UiEvent
import com.nowiwr01.languator.base.UiState
import com.nowiwr01.languator.logic.errors.SignInTextError

class SignInContract {

    sealed class Event: UiEvent {
        data class OnSignInClicked(val email: String, val password: String): SignInContract.Event()
    }

    data class State(
        val signInState: SignInState
    ): UiState

    sealed class SignInState {
        object Idle: SignInState()
        object SignInInProcess: SignInState()
        data class Success(val user: FirebaseUser): SignInState()
    }

    sealed class Effect: UiEffect {
        data class InputError(val error: SignInTextError): Effect()
        data class LoginError(val message: String): Effect()
    }
}