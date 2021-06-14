package com.nowiwr01.languator.screens.auth.signup

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.nowiwr01.languator.R
import com.nowiwr01.languator.base.BaseFragment
import com.nowiwr01.languator.databinding.FragmentSignUpBinding
import com.nowiwr01.languator.domain.UserData
import com.nowiwr01.languator.extensions.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.coroutines.flow.collect
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf

class SignUpFragment: BaseFragment<FragmentSignUpBinding>() {

    override val layoutResId = R.layout.fragment_sign_up

    private val viewModel: SignUpViewModel by sharedViewModel()
    private val controller: SignUpController by currentScope.inject {
        parametersOf(this)
    }

    override fun setViews() {
        controller.getTextFields().forEach {
            controller.setTextChangedCallback(it)
        }
    }

    override fun setListeners() {
        binding.signUp.setOnClickListener {
            val userData = controller.getUserData()
            signUpIfValid(userData)
        }
    }

    private fun signUpIfValid(userData: UserData) {
        controller.setDefaultAll()
        val error = viewModel.isUserInputValid(userData)
        if (error == null) {
            viewModel.setEvent(
                SignUpContract.Event.OnSignUpClicked(userData.email, userData.password)
            )
        } else {
            viewModel.setEffect(
                SignUpContract.Effect.InputError(error)
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

    private fun proceedEffect(effect: SignUpContract.Effect) {
        when (effect) {
            is SignUpContract.Effect.CreateUserError -> {
                showSnackbar(effect.message)
            }
            is SignUpContract.Effect.InputError -> {
                controller.setErrorByNumbers(effect.error.list)
                showSnackbar(effect.error.message)
            }
        }
    }

    private fun proceedState(state: SignUpContract.State) {
        when (state.signUpState) {
            is SignUpContract.SignUpState.Idle -> {
                progress_sign_up.setGone()
                text_sign_up.setVisible()
            }
            is SignUpContract.SignUpState.SignUpInProcess -> {
                progress_sign_up.setVisible()
                text_sign_up.setGone()
            }
            is SignUpContract.SignUpState.Success -> {
                Log.d("Testik", "proceedState(), Success, user = ${state.signUpState.user}")
            }
        }
    }
}