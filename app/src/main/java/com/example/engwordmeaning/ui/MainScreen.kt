package com.example.engwordmeaning.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.engwordmeaning.viewmodel.MainViewModel
import com.example.engwordmeaning.ui.components.TopAppBarComponent
import com.example.engwordmeaning.ui.components.DrawerComponent
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import com.example.engwordmeaning.R
import com.example.engwordmeaning.repository.DictionaryRepository

@Composable
fun MainScreen(navController: NavHostController) {
    val context = LocalContext.current
    val viewModel: MainViewModel = viewModel { MainViewModel(DictionaryRepository()) }

    val searchQuery = rememberSaveable { mutableStateOf("") }
    val searchResult by viewModel.searchResult.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val isLoading by viewModel.loading.collectAsState()

    val scaffoldState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = scaffoldState,
        drawerContent = { DrawerComponent(navController, scaffoldState, coroutineScope) }
    ) {
        Scaffold(
            topBar = { TopAppBarComponent(navController, scaffoldState, coroutineScope) }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                item {
                    OutlinedTextField(
                        value = searchQuery.value,
                        onValueChange = { searchQuery.value = it },
                        label = { Text(stringResource(id = R.string.search_hint)) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { viewModel.searchWord(searchQuery.value, context) },
                        enabled = searchQuery.value.isNotBlank(),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(id = R.string.search_button))
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }

                when {
                    isLoading -> item { CircularProgressIndicator(modifier = Modifier.padding(16.dp)) }
                    errorMessage != null -> item { Text(errorMessage!!, color = MaterialTheme.colorScheme.error) }
                    searchResult != null -> {
                        items(searchResult ?: emptyList()) { word ->
                            Text(word.word, style = MaterialTheme.typography.headlineMedium)
                            Spacer(modifier = Modifier.height(8.dp))

                            //// phonetics
                            word.meanings.forEach { meaning ->
                                Column(modifier = Modifier.padding(bottom = 12.dp)) {
                                    Text(
                                        text = meaning.partOfSpeech,
                                        style = MaterialTheme.typography.titleMedium
                                    )

                                    val matchingPhonetics = word.phonetics.filter { it.text != null }
                                    if (matchingPhonetics.isNotEmpty()) {
                                        Text(
                                            text = matchingPhonetics.joinToString { it.text ?: "" },
                                            style = MaterialTheme.typography.bodyLarge
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(4.dp))

                                    //////////// definitions
                                    meaning.definitions.forEachIndexed { index, definition ->
                                        Text(
                                            text = "${index + 1}. ${definition.definition}",
                                            style = MaterialTheme.typography.bodyMedium
                                        )

                                        Spacer(modifier = Modifier.height(4.dp))
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}
