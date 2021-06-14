package com.nowiwr01.languator.screens.auth.login

import com.nowiwr01.languator.logic.interactors.FirebaseInteractor
import com.nowiwr01.languator.logic.interactors.UserDataInteractor
import com.nowiwr01.languator.logic.repository.impl.FirebaseRepositoryImpl
import com.nowiwr01.languator.logic.repository.impl.UserDataRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val moduleSignIn = module {
    scope(named<SignInFragment>()) {
        scoped { params ->
            SignInEmailProvider(params[0])
        }
        scoped { params ->
            SignInPasswordProvider(params[0])
        }
        scoped { params ->
            val fragment: SignInFragment = params[0]
            SignInController(
                get<SignInEmailProvider> { parametersOf(fragment) },
                get<SignInPasswordProvider> { parametersOf(fragment) },
            )
        }
    }

    viewModel {
        SignInViewModel(
            FirebaseInteractor(
                FirebaseRepositoryImpl(get())
            ),
            UserDataInteractor(
                UserDataRepositoryImpl()
            )
        )
    }
}