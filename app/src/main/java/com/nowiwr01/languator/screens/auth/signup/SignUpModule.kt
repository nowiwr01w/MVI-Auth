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
            SignUpEmailProvider(params[0])
        }
        scoped { params ->
            SignUpUsernameProvider(params[0])
        }
        scoped { params ->
            SignUpPasswordProvider(params[0])
        }
        scoped { params ->
            SignUpPasswordRepeatProvider(params[0])
        }
        scoped { params ->
            val fragment: SignUpFragment = params[0]
            SignUpController(
                get<SignUpEmailProvider> { parametersOf(fragment) },
                get<SignUpUsernameProvider> { parametersOf(fragment) },
                get<SignUpPasswordProvider> { parametersOf(fragment) },
                get<SignUpPasswordRepeatProvider> { parametersOf(fragment) }
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