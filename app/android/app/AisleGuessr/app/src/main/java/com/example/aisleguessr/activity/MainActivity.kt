package com.example.aisleguessr.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.aisleguessr.ui.AisleGuessrApp
import com.example.aisleguessr.ui.theme.AisleGuessrTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AisleGuessrTheme {
                Surface(modifier = Modifier.fillMaxSize()){
                    AisleGuessrApp()
                }
            }
        }
    }
}