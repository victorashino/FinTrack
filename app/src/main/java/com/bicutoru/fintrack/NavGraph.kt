package com.bicutoru.fintrack

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bicutoru.auth.AuthScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "authScreen") {

        composable(route = "authScreen") {
            AuthScreen(navController)
        }
    }

}