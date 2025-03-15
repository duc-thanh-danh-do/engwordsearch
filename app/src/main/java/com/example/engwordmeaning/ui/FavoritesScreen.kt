package com.example.engwordmeaning.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.engwordmeaning.ui.components.DrawerComponent
import com.example.engwordmeaning.ui.components.TopAppBarComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(navController: NavHostController) {
    val scaffoldState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = scaffoldState,
        drawerContent = { DrawerComponent(navController, scaffoldState, coroutineScope) }
    ) {
        Scaffold(
            topBar = { TopAppBarComponent(navController, scaffoldState, coroutineScope) }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Text("Favorite words will appear here.", style = MaterialTheme.typography.headlineMedium)
            }
        }
    }
}