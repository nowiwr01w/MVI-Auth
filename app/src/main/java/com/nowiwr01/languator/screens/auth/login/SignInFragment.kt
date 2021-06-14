package com.nowiwr01.languator.screens.auth.login

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.nowiwr01.languator.R
import com.nowiwr01.languator.base.BaseFragment
import com.nowiwr01.languator.databinding.FragmentSignInBinding
import com.nowiwr01.languator.domain.UserDataSignIn
import com.nowiwr01.languator.extensions.setGone
import com.nowiwr01.languator.extensions.setVisible
import com.nowiwr01.languator.extensions.showSnackbar
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.coroutines.flow.collect
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf

class SignInFragment: BaseFragment<FragmentSignInBinding>() {

    override val layoutResId = R.layout.fragment_sign_in

    private val viewModel: SignInViewModel by sharedViewModel()
    private val controller: SignInController by currentScope.inject {
        parametersOf(this)
    }

    override fun setViews() {
        controller.setTextChangedCallback()
    }

    override fun setListeners() {
        login.setOnClickListener {
            val userData = controller.getUserData()
            signInIfValid(userData)
        }
    }

    private fun signInIfValid(userData: UserDataSignIn) {
        val error = viewModel.isUserInputValid(userData)
        if (error == null) {
            viewModel.setEvent(
                SignInContract.Event.OnSignInClicked(userData.email, userData.password)
            )
        } else {
            viewModel.setEffect(
                SignInContract.Effect.InputError(error)
            )
        }
    }

    override fun setObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                proceedState(it)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.effect.collect {
                proceedEffect(it)
            }
        }
    }

    private fun proceedState(state: SignInContract.State) {
        when (state.signInState) {
            is SignInContract.SignInState.Idle -> {
                text_sign_in.setVisible()
                progress_sign_in.setGone()
            }
            is SignInContract.SignInState.SignInInProcess -> {
                text_sign_in.setGone()
                progress_sign_in.setVisible()

            }
            is SignInContract.SignInState.Success -> {
                Log.d("Testik", "proceedState(), Success, user = ${state.signInState.user}")
            }
        }
    }

    private fun proceedEffect(effect: SignInContract.Effect) {
        when (effect) {
            is SignInContract.Effect.LoginError -> {
                showSnackbar(effect.message)
            }
            is SignInContract.Effect.InputError -> {
                controller.setErrorByNumbers(effect.error.list)
                showSnackbar(effect.error.message)
            }
        }
    }
}