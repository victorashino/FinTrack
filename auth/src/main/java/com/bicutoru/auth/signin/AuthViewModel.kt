package com.bicutoru.auth.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AuthenticationResult {
    object Success : AuthenticationResult()
    object Null : AuthenticationResult()
    data class Error(val message: String) : AuthenticationResult()
}

class AuthViewModel @Inject constructor() : ViewModel() {

    private val _authenticationStatus =
        MutableStateFlow<AuthenticationResult>(AuthenticationResult.Null)
    val authenticationStatus: StateFlow<AuthenticationResult> = _authenticationStatus

    fun signInWithEmailAndPassword(email: String, pwd: String) {
        if (!(isEmailValid(email) && isPasswordValid(pwd))) {
            _authenticationStatus.value = AuthenticationResult.Error("Invalid email or password.")
            Log.e("AuthViewModel", "Invalid email or password.")
            return
        }

        try {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _authenticationStatus.value = AuthenticationResult.Success
                        Log.e("AuthViewModel", "Authentication success.")
                    } else {
                        _authenticationStatus.value =
                            AuthenticationResult.Error("Authentication failed. Please check your credentials.")
                        Log.e("AuthViewModel", "Authentication failed.")
                    }
                }
        } catch (e: Exception) {
            _authenticationStatus.value = AuthenticationResult.Error("Error occurred: ${e.message}")
            Log.e("AuthViewModel", "Error occurred: ${e.message}")
        }
    }

    fun clearErrorAfterDelay() {
        viewModelScope.launch {
            delay(3000)
            _authenticationStatus.value = AuthenticationResult.Null
        }
    }

    private fun isEmailValid(email: String): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isPasswordValid(password: String): Boolean = password.length >= 6
}

