package com.bicutoru.auth

import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bicutoru.design_system.R
import com.bicutoru.design_system.theme.Black

@Composable
fun AuthScreen(navController: NavController) {
    AuthContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AuthContent(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .imePadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Black)
                .padding(bottom = 100.dp, top = 150.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .size(160.dp)
                    .padding(16.dp)
            )

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("E-mail") },
                isError = !isEmailValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .clip(RoundedCornerShape(8.dp)),
                textStyle = MaterialTheme.typography.bodyMedium,
                keyboardActions = KeyboardActions(
                    onNext = {

                    }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                isError = !isPasswordValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .clip(RoundedCornerShape(8.dp)),
                textStyle = MaterialTheme.typography.bodyMedium,
                visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
                keyboardActions = KeyboardActions(
                    onDone = {

                    }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier.height(200.dp)
            ) {}

            Spacer(modifier = Modifier.height(16.dp))
        }

        Button(
            onClick = {
                isEmailValid = email.contains("@")
                isPasswordValid = password.length >= 6
                if (isEmailValid && isPasswordValid) {

                } else {
                    Toast.makeText(
                        navController.context,
                        "Invalid email or password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
                .height(54.dp)
                .clip(RoundedCornerShape(0.dp))
                .align(Alignment.BottomCenter)
                .navigationBarsPadding(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Text("Login")
        }
    }
}