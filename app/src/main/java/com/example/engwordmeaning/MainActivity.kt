package com.example.engwordmeaning

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.engwordmeaning.navigation.AppNavigation
import com.example.engwordmeaning.ui.theme.EngWordMeaningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkMode by remember { mutableStateOf(false) }
            var selectedLanguage by remember { mutableStateOf("English") }
            var key by remember { mutableIntStateOf(0) }

            val context = LocalContext.current

            LaunchedEffect(selectedLanguage) {
                val newLocale = when (selectedLanguage) {
                    "Vietnamese" -> android.icu.util.ULocale("vi")
                    else -> android.icu.util.ULocale("en")
                }

                val config = Configuration(context.resources.configuration)
                config.setLocale(newLocale.toLocale())
                context.resources.updateConfiguration(config, context.resources.displayMetrics)
                key++
            }

            EngWordMeaningTheme(darkTheme = isDarkMode) {
                val navController = rememberNavController()

                AppNavigation(
                    navController = navController,
                    isDarkMode = isDarkMode,
                    onThemeChange = { isDarkMode = it },
                    selectedLanguage = selectedLanguage,
                    onLanguageChange = { selectedLanguage = it },
                    key = key
                )
            }
        }
    }
}
