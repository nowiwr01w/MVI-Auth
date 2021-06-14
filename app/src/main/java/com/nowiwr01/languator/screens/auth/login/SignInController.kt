package com.nowiwr01.languator.screens.auth.login

import android.widget.EditText
import com.nowiwr01.languator.base.ViewProvider
import com.nowiwr01.languator.domain.UserDataSignIn
import com.nowiwr01.languator.extensions.doOnTextChanged
import com.nowiwr01.languator.extensions.setDefault
import com.nowiwr01.languator.extensions.setError

class SignInController(
    private val emailProvider: ViewProvider<EditText>,
    private val passwordProvider: ViewProvider<EditText>
) {

    fun getUserData() = UserDataSignIn(
        emailProvider.view.text.toString(),
        passwordProvider.view.text.toString(),
    )

    fun setErrorByNumbers(numbers: List<Int>) {
        numbers.forEach {
            when (it) {
                1 -> emailProvider.view.setError()
                2 -> passwordProvider.view.setError()
            }
        }
    }

    fun setTextChangedCallback() {
        listOf(emailProvider.view, passwordProvider.view).forEach {
            it.doOnTextChanged { it.setDefault() }
        }
    }


}