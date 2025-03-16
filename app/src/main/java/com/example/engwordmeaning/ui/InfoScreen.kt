package com.example.engwordmeaning.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.engwordmeaning.R
import com.example.engwordmeaning.ui.components.DrawerComponent
import com.example.engwordmeaning.ui.components.TopAppBarComponent

@Composable
fun InfoScreen(navController: NavHostController) {
    val scaffoldState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    ModalNavigationDrawer(
        drawerState = scaffoldState,
        drawerContent = { DrawerComponent(navController, scaffoldState, coroutineScope) }
    ) {
        Scaffold(
            topBar = { TopAppBarComponent(navController, scaffoldState, coroutineScope) }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ///////////// description
                item {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = stringResource(R.string.app_description),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Divider(modifier = Modifier.padding(vertical = 16.dp))
                }
                ////// version
                item {
                    Text(
                        text = stringResource(R.string.version_info),
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = stringResource(R.string.app_version),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Divider(modifier = Modifier.padding(vertical = 16.dp))
                }
                ////////// features
                item {
                    Text(
                        text = stringResource(R.string.features),
                        style = MaterialTheme.typography.titleMedium
                    )

                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("* " + stringResource(R.string.feature_search))
                       // Text("* " + stringResource(R.string.feature_favorites))
                        Text("* " + stringResource(R.string.feature_dark_mode))
                        Text("* " + stringResource(R.string.feature_language_switch))
                    }

                    Divider(modifier = Modifier.padding(vertical = 16.dp))
                }
                ///////// API
                item {
                    Text(
                        text = stringResource(R.string.api_source),
                        style = MaterialTheme.typography.titleMedium
                    )

                    val apiUrl = stringResource(R.string.api_url)
                    Text(
                        text = "GET: $apiUrl",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(4.dp)
                            .clickable {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(apiUrl))
                                context.startActivity(intent)
                            }
                    )

                    Divider(modifier = Modifier.padding(vertical = 16.dp))
                }
                ////////// contact
                item {
                    Text(
                        text = stringResource(R.string.credits),
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = stringResource(R.string.contact_info),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}
