package com.nowiwr01.languator.screens.auth.signup

import android.widget.EditText
import com.nowiwr01.languator.base.ViewProvider
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpEmailProvider(private val fragment: SignUpFragment) : ViewProvider<EditText> {
    override val view: EditText
        get() = fragment.email
}

class SignUpUsernameProvider(private val fragment: SignUpFragment) : ViewProvider<EditText> {
    override val view: EditText
        get() = fragment.login
}

class SignUpPasswordProvider(private val fragment: SignUpFragment) : ViewProvider<EditText> {
    override val view: EditText
        get() = fragment.password0
}

class SignUpPasswordRepeatProvider(private val fragment: SignUpFragment) : ViewProvider<EditText> {
    override val view: EditText
        get() = fragment.password1
}