package com.nowiwr01.languator.screens.auth.signup

import android.widget.EditText
import com.nowiwr01.languator.base.ViewProvider
import com.nowiwr01.languator.domain.UserData
import com.nowiwr01.languator.extensions.doOnTextChanged
import com.nowiwr01.languator.extensions.setDefault
import com.nowiwr01.languator.extensions.setError

class SignUpController(
    private val emailProvider: ViewProvider<EditText>,
    private val usernameProvider: ViewProvider<EditText>,
    private val passwordProvider: ViewProvider<EditText>,
    private val passwordRepeatProvider: ViewProvider<EditText>
) {

    fun getUserData() = UserData.createUserData(
        emailProvider.view.text.toString(),
        usernameProvider.view.text.toString(),
        passwordProvider.view.text.toString(),
        passwordRepeatProvider.view.text.toString()
    )

    fun setDefaultAll() {
        emailProvider.view.setDefault()
        usernameProvider.view.setDefault()
        passwordProvider.view.setDefault()
        passwordRepeatProvider.view.setDefault()
    }

    fun setErrorByNumbers(numbers: List<Int>) {
        numbers.forEach {
            when (it) {
                1 -> emailProvider.view.setError()
                2 -> usernameProvider.view.setError()
                3 -> passwordProvider.view.setError()
                4 -> passwordRepeatProvider.view.setError()
            }
        }
    }

    fun setTextChangedCallback(editText: EditText) {
        editText.doOnTextChanged {
            editText.setDefault()
        }
    }

    fun getTextFields() = listOf(
        emailProvider.view, usernameProvider.view, passwordProvider.view, passwordRepeatProvider.view
    )
}