package com.nowiwr01.languator.screens.auth.signup

import com.google.firebase.auth.FirebaseAuth
import com.nowiwr01.languator.logic.interactors.FirebaseInteractor
import com.nowiwr01.languator.logic.interactors.UserDataInteractor
import com.nowiwr01.languator.logic.repository.FirebaseRepository
import com.nowiwr01.languator.logic.repository.UserDataRepository
import com.nowiwr01.languator.logic.repository.impl.FirebaseRepositoryImpl
import com.nowiwr01.languator.logic.repository.impl.UserDataRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val moduleSignUp = module {

    scope(named<SignUpFragment>()) {
        scoped { params ->
            EmailProvider(params[0])
        }
        scoped { params ->
            UsernameProvider(params[0])
        }
        scoped { params ->
            PasswordProvider(params[0])
        }
        scoped { params ->
            PasswordRepeatProvider(params[0])
        }
        scoped { params ->
            val fragment: SignUpFragment = params[0]
            SignUpController(
                get<EmailProvider> { parametersOf(fragment) },
                get<UsernameProvider> { parametersOf(fragment) },
                get<PasswordProvider> { parametersOf(fragment) },
                get<PasswordRepeatProvider> { parametersOf(fragment) }
            )
        }
    }

    single {
        FirebaseAuth.getInstance()
    }
    single<UserDataRepository> {
        UserDataRepositoryImpl()
    }
    single<FirebaseRepository> {
        FirebaseRepositoryImpl(get())
    }

    viewModel {
        SignUpViewModel(
            FirebaseInteractor(
                FirebaseRepositoryImpl(get())
            ),
            UserDataInteractor(
                UserDataRepositoryImpl()
            )
        )
    }
}