package com.bicutoru.fintrack

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bicutoru.auth.signin.AuthScreen
import com.bicutoru.auth.signup.SignUpScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "authScreen") {

        composable(route = "authScreen") {
            AuthScreen(navController)
        }

        composable(route = "signUpScreen") {
            SignUpScreen(navController)
        }
    }

}