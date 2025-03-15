package com.example.engwordmeaning.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Info
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent(navController: NavHostController, scaffoldState: DrawerState, coroutineScope: CoroutineScope) {
    val currentScreen = navController.currentBackStackEntryAsState().value?.destination?.route ?: "main"

    CenterAlignedTopAppBar(
        title = { Text(getScreenTitle(currentScreen)) },
        navigationIcon = {
            IconButton(onClick = { coroutineScope.launch { scaffoldState.open() } }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Open Drawer")
            }
        },
        actions = {
            var menuExpanded by remember { mutableStateOf(false) }

            IconButton(onClick = { menuExpanded = !menuExpanded }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "More Options")
            }

            DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }) {
                DropdownMenuItem(
                    text = { Text("Settings") },
                    onClick = {
                        menuExpanded = false
                        navController.navigate("settings")
                    }
                )
                DropdownMenuItem(
                    text = { Text("Info") },
                    onClick = {
                        menuExpanded = false
                        navController.navigate("info")
                    }
                )
            }
        }
    )
}

fun getScreenTitle(route: String): String {
    return when (route) {
        "main" -> "English Dictionary"
        "favorites" -> "Favorites"
        "settings" -> "Settings"
        "info" -> "Info"
        else -> "EngWordMeaning"
    }
}
