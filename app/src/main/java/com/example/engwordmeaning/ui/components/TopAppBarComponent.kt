package com.example.engwordmeaning.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import com.example.engwordmeaning.R
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent(
    navController: NavHostController,
    scaffoldState: DrawerState,
    coroutineScope: CoroutineScope
) {
    val currentScreen = navController.currentBackStackEntryAsState().value?.destination?.route ?: "main"

    CenterAlignedTopAppBar(
        title = { Text(getScreenTitle(currentScreen)) },
        navigationIcon = {
            IconButton(onClick = { coroutineScope.launch { scaffoldState.open() } }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = stringResource(R.string.open_drawer))
            }
        },
        actions = {
            var menuExpanded by remember { mutableStateOf(false) }

            IconButton(onClick = { menuExpanded = !menuExpanded }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = stringResource(R.string.more_options))
            }

            DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }) {
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.settings_screen)) },
                    onClick = {
                        menuExpanded = false
                        navController.navigate("settings")
                    }
                )
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.info_screen)) },
                    onClick = {
                        menuExpanded = false
                        navController.navigate("info")
                    }
                )
            }
        }
    )
}

@Composable
fun getScreenTitle(route: String): String {
    return when (route) {
        "main" -> stringResource(R.string.main_screen)
        "favorites" -> stringResource(R.string.favorites_screen)
        "settings" -> stringResource(R.string.settings_screen)
        "info" -> stringResource(R.string.info_screen)
        else -> stringResource(R.string.app_name)
    }
}
