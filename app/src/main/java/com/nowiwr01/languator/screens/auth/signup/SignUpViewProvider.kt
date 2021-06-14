package com.nowiwr01.languator.screens.auth.signup

import android.widget.EditText
import com.nowiwr01.languator.base.ViewProvider
import kotlinx.android.synthetic.main.fragment_sign_up.*

class EmailProvider(private val fragment: SignUpFragment) : ViewProvider<EditText> {
    override val view: EditText
        get() = fragment.email
}

class UsernameProvider(private val fragment: SignUpFragment) : ViewProvider<EditText> {
    override val view: EditText
        get() = fragment.login
}

class PasswordProvider(private val fragment: SignUpFragment) : ViewProvider<EditText> {
    override val view: EditText
        get() = fragment.password0
}

class PasswordRepeatProvider(private val fragment: SignUpFragment) : ViewProvider<EditText> {
    override val view: EditText
        get() = fragment.password1
}