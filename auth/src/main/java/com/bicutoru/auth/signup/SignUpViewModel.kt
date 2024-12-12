package com.bicutoru.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

sealed class RegisterResult {
    object Success : RegisterResult()
    object Null : RegisterResult()
    data class Error(val message: String) : RegisterResult()
}

class SignUpViewModel @Inject constructor() : ViewModel() {


    private val _registerStatus =
        MutableStateFlow<RegisterResult>(RegisterResult.Null)
    val registerStatus: StateFlow<RegisterResult> = _registerStatus

    fun createUserWithEmailAndPassword(email: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            when {
                !isEmailValid(email) -> {
                    _registerStatus.value = RegisterResult.Error("Email inválido.")
                }
                password != confirmPassword -> {
                    _registerStatus.value = RegisterResult.Error("As senhas não coincidem.")
                }
                !isPasswordStrong(password) -> {
                    _registerStatus.value = RegisterResult.Error("A senha precisa ter no mínimo 6 caracteres.")
                }
                else -> {
                    try {
                        registerUserWithFirebase(email, password)
                        _registerStatus.value = RegisterResult.Success
                    } catch (e: Exception) {
                        _registerStatus.value = RegisterResult.Error("Erro ao registrar: ${e.message}")
                    }
                }
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordStrong(password: String): Boolean {
        return password.length >= 6
    }

    private suspend fun registerUserWithFirebase(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()
    }
}