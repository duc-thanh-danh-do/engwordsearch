package com.example.engwordmeaning.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Info
import com.example.engwordmeaning.R
import androidx.compose.ui.platform.LocalConfiguration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerComponent(
    navController: NavHostController,
    scaffoldState: DrawerState,
    coroutineScope: CoroutineScope
) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val drawerWidth = screenWidth * (5f / 6f)

    ModalDrawerSheet(
        modifier = Modifier
            .width(drawerWidth)
            .fillMaxHeight()
    ) {
        //// Colored Header Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(R.string.navigation),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.app_description),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        ////// navigation
        DrawerItem(stringResource(R.string.main_screen), navController, scaffoldState, coroutineScope, "main", Icons.Filled.Home)
        DrawerItem(stringResource(R.string.favorites_screen), navController, scaffoldState, coroutineScope, "favorites", Icons.Filled.Favorite)
        DrawerItem(stringResource(R.string.settings_screen), navController, scaffoldState, coroutineScope, "settings", Icons.Filled.Settings)
        DrawerItem(stringResource(R.string.info_screen), navController, scaffoldState, coroutineScope, "info", Icons.Filled.Info)
    }
}

@Composable
fun DrawerItem(
    text: String,
    navController: NavHostController,
    scaffoldState: DrawerState,
    coroutineScope: CoroutineScope,
    route: String,
    icon: ImageVector
) {
    val isSelected = navController.currentDestination?.route == route

    NavigationDrawerItem(
        label = { Text(text, style = MaterialTheme.typography.bodyLarge) },
        icon = { Icon(imageVector = icon, contentDescription = text) },
        selected = isSelected,
        onClick = {
            coroutineScope.launch { scaffoldState.close() }
            navController.navigate(route)
        },
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
    )
}
