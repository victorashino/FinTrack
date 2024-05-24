package com.example.fintrack.view.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fintrack.R
import com.example.fintrack.utils.BiometricHelper
import com.example.fintrack.view.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (BiometricHelper.isBicometricAvalieble(this)) {

            val executor = ContextCompat.getMainExecutor(this)

            val bio = BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    super.onAuthenticationSucceeded(result)
                    finish()
                }
            })

            val info = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Title")
                .setSubtitle("SubTitle")
                .setDescription("Description")
                .setNegativeButtonText("Cancel")
                .build()

            bio.authenticate(info)
        } else {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }

    }
}