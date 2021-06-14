package com.nowiwr01.languator.screens.auth.signup

import com.google.firebase.auth.FirebaseUser
import com.nowiwr01.languator.base.UiEffect
import com.nowiwr01.languator.base.UiEvent
import com.nowiwr01.languator.base.UiState
import com.nowiwr01.languator.logic.errors.SignUpTextError

class SignUpContract {

    sealed class Event: UiEvent {
        data class OnSignUpClicked(val email: String, val password: String): Event()
    }

    data class State(
        val signUpState: SignUpState
    ): UiState

    sealed class SignUpState {
        object Idle: SignUpState()
        object SignUpInProcess: SignUpState()
        data class Success(val user: FirebaseUser): SignUpState()
    }

    sealed class Effect: UiEffect {
        data class InputError(val error: SignUpTextError): Effect()
        data class CreateUserError(val message: String): Effect()
    }
}