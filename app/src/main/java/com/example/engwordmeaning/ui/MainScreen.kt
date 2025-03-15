package com.example.engwordmeaning.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.engwordmeaning.viewmodel.MainViewModel
import com.example.engwordmeaning.ui.components.TopAppBarComponent
import com.example.engwordmeaning.ui.components.DrawerComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.saveable.rememberSaveable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel = viewModel()) {
    val searchQuery = rememberSaveable { mutableStateOf("") }
    val searchResult by viewModel.searchResult.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    val scaffoldState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = scaffoldState,
        drawerContent = { DrawerComponent(navController, scaffoldState, coroutineScope) }
    ) {
        Scaffold(
            topBar = { TopAppBarComponent(navController, scaffoldState, coroutineScope) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery.value,
                    onValueChange = { searchQuery.value = it },
                    label = { Text("Enter an English word") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { viewModel.searchWord(searchQuery.value) },
                    enabled = searchQuery.value.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Search")
                }

                Spacer(modifier = Modifier.height(16.dp))

                errorMessage?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                }

                searchResult?.takeIf { it.isNotEmpty() }?.firstOrNull()?.let { word ->
                    Text("Word: ${word.word}", style = MaterialTheme.typography.headlineMedium)
                    word.meanings.forEach { meaning ->
                        Text("${meaning.partOfSpeech}: ${meaning.definitions.firstOrNull()?.definition ?: "No definition"}")
                    }
                }
            }
        }
    }
}
