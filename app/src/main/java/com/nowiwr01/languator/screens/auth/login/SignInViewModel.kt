package com.nowiwr01.languator.screens.auth.login

import com.google.firebase.auth.FirebaseUser
import com.nowiwr01.languator.base.BaseViewModel
import com.nowiwr01.languator.base.mapBoth
import com.nowiwr01.languator.domain.UserDataSignIn
import com.nowiwr01.languator.logic.errors.SignInError
import com.nowiwr01.languator.logic.errors.SignInTextError
import com.nowiwr01.languator.logic.interactors.FirebaseInteractor
import com.nowiwr01.languator.logic.interactors.UserDataInteractor
import kotlinx.coroutines.launch

class SignInViewModel(
    private val firebaseInteractor: FirebaseInteractor,
    private val userDataInteractor: UserDataInteractor
): BaseViewModel<SignInContract.Event, SignInContract.State, SignInContract.Effect>() {

    fun setEffect(effect: SignInContract.Effect) {
        setEffect { effect }
    }

    override fun createInitialState(): SignInContract.State {
        return SignInContract.State(
            SignInContract.SignInState.Idle
        )
    }

    override fun handleEvent(event: SignInContract.Event) {
        when (event) {
            is SignInContract.Event.OnSignInClicked -> {
                login(event.email, event.password)
            }
        }
    }

    fun isUserInputValid(userData: UserDataSignIn): SignInTextError? {
        return userDataInteractor.isSignInDataValid(userData)
    }

    private fun login(email: String, password: String) {
        launch {
            firebaseInteractor.login(email, password).mapBoth(::proceedUser, ::onError)
        }
    }

    private fun onError(error: SignInError) {
        when (error) {
            is SignInError.LoginUserError -> {
                setEffect {
                    SignInContract.Effect.LoginError("Проверьте соединение с интернетом и попробуйте авторизироваться снова")
                }
            }
        }
    }

    private fun proceedUser(user: FirebaseUser?) {
        if (user != null) {
            setState {
                SignInContract.State(
                    SignInContract.SignInState.Success(user)
                )
            }
        }
    }
}