package com.nowiwr01.languator.screens.auth.signup

import com.google.firebase.auth.FirebaseUser
import com.nowiwr01.languator.base.BaseViewModel
import com.nowiwr01.languator.base.mapBoth
import com.nowiwr01.languator.domain.UserDataSignUp
import com.nowiwr01.languator.logic.errors.SignUpTextError
import com.nowiwr01.languator.logic.errors.SignUpError
import com.nowiwr01.languator.logic.interactors.FirebaseInteractor
import com.nowiwr01.languator.logic.interactors.UserDataInteractor
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val firebaseInteractor: FirebaseInteractor,
    private val userDataInteractor: UserDataInteractor
): BaseViewModel<SignUpContract.Event, SignUpContract.State, SignUpContract.Effect>() {

    fun setEffect(effect: SignUpContract.Effect) {
        setEffect { effect }
    }

    override fun createInitialState(): SignUpContract.State {
        return SignUpContract.State(
            SignUpContract.SignUpState.Idle
        )
    }

    override fun handleEvent(event: SignUpContract.Event) {
        when (event) {
            is SignUpContract.Event.OnSignUpClicked -> {
                signUp(event.email, event.password)
            }
        }
    }

    fun isUserInputValid(userData: UserDataSignUp): SignUpTextError? {
        return userDataInteractor.isSignUpDataValid(userData)
    }

    private fun signUp(email: String, password: String) {
        launch {
            firebaseInteractor.signUp(email, password).mapBoth(::proceedUser, ::onError)
        }
    }

    private fun proceedUser(user: FirebaseUser?) {
        if (user != null) {
            setState {
                SignUpContract.State(
                    SignUpContract.SignUpState.Success(user)
                )
            }
        }
    }

    private fun onError(error: SignUpError) {
        when (error) {
            is SignUpError.CreateUserError -> {
                setEffect {
                    SignUpContract.Effect.CreateUserError("Не удалось создать аккаунт")
                }
            }
        }
    }
}