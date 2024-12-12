package com.bicutoru.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bicutoru.design_system.R
import com.bicutoru.design_system.components.InputEmail
import com.bicutoru.design_system.components.InputPassword
import com.bicutoru.design_system.theme.Black
import com.bicutoru.design_system.theme.josefinSans


@Composable
fun SignUpScreen(navController: NavController) {
    SignUpContent(navController)
}

@Composable
fun SignUpContent(
    navController: NavController,
    signUpViewModel: SignUpViewModel = hiltViewModel()
    ) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val isEmailValid = email.contains("@") && email.contains(".")
    val isPasswordValid = password.length >= 6
    val isConfirmPasswordValid = confirmPassword == password && confirmPassword.isNotEmpty()

    val isFormValid = isEmailValid && isPasswordValid && isConfirmPasswordValid

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .imePadding()
    ) {

        Text(
            text = "Sign Up",
            color = Color.Blue,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp, end = 16.dp),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Black)
                .padding(top = 50.dp, bottom = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .size(160.dp)
                    .padding(16.dp)
            )

            InputEmail(
                email = email,
                onEmailChange = {
                    email = it
                },
                modifier = Modifier.padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            InputPassword(
                password = password,
                onPasswordChange = {
                    password = it
                },
                modifier = Modifier.padding(top = 16.dp)
            )

            InputPassword(
                textLabel = "Confirm password",
                password = confirmPassword,
                onPasswordChange = {
                    confirmPassword = it
                },
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        Button(
            onClick = {
                signUpViewModel.createUserWithEmailAndPassword(email, password, confirmPassword)
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
                .height(54.dp)
                .align(Alignment.BottomCenter)
                .navigationBarsPadding(),
            enabled = true,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isFormValid) Color(
                    0xFF1536C7
                ) else Color.LightGray,
                contentColor = if (isFormValid) Color.White else Color.DarkGray
            )
        ) {
            Text(
                text = "Sign Up",
                style = TextStyle(
                    fontFamily = josefinSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )
        }
    }
}
