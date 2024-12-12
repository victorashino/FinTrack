package com.bicutoru.auth

import com.bicutoru.auth.signin.AuthViewModel
import com.bicutoru.auth.signup.SignUpViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AuthModule {

    @Provides
    @ViewModelScoped
    fun provideAuthViewModel(): AuthViewModel {
        return AuthViewModel()
    }

    @Provides
    @ViewModelScoped
    fun provideSignUpViewModel(): SignUpViewModel {
        return SignUpViewModel()
    }
}