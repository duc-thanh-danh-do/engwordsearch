package com.example.engwordmeaning.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.compose.ui.Modifier
import com.example.engwordmeaning.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerComponent(
    navController: NavHostController,
    scaffoldState: DrawerState,
    coroutineScope: CoroutineScope
) {
    ModalDrawerSheet {
        Text(stringResource(R.string.navigation), style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(16.dp))

        Divider()

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
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    TextButton(
        onClick = {
            coroutineScope.launch { scaffoldState.close() }
            navController.navigate(route)
        },
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row {
            Icon(icon, contentDescription = text)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
