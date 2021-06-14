package com.nowiwr01.languator.screens.auth.login

import android.widget.EditText
import com.nowiwr01.languator.base.ViewProvider
import kotlinx.android.synthetic.main.fragment_sign_in.email
import kotlinx.android.synthetic.main.fragment_sign_in.password0

class SignInEmailProvider(private val fragment: SignInFragment) : ViewProvider<EditText> {
    override val view: EditText
        get() = fragment.email
}

class SignInPasswordProvider(private val fragment: SignInFragment) : ViewProvider<EditText> {
    override val view: EditText
        get() = fragment.password0
}