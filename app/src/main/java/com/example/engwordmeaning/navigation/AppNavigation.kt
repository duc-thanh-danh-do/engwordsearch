package com.example.engwordmeaning.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.engwordmeaning.ui.FavoritesScreen
import com.example.engwordmeaning.ui.MainScreen
import com.example.engwordmeaning.ui.InfoScreen
import com.example.engwordmeaning.ui.SettingsScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("favorites") { FavoritesScreen(navController) }
        composable("info") { InfoScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
    }
}
