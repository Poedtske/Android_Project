package com.example.android_project.presentation.ober.table

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.android_project.presentation.components.CategoryCard
import com.example.android_project.presentation.components.CategoryEvent
import com.example.android_project.presentation.components.ClientCard
import com.example.android_project.presentation.components.ClientEvent
import com.example.android_project.utils.Screen
import kotlinx.coroutines.launch

@Composable
fun TableOberScreen(navController: NavController, tableId: Int, viewModel: TableOberViewModel= hiltViewModel()) {
    val snackbarHostState = remember{ SnackbarHostState() }
    val scope= rememberCoroutineScope()

    viewModel.loadTable(tableId)

    Scaffold(
        snackbarHost = {SnackbarHost(snackbarHostState)},
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AddEditClientScreen.route)
            },
                modifier = Modifier
                    .background(Color.White)) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add a Client")
            }
        }
    ){ contentPadding ->
        Column(modifier = Modifier
            .padding(contentPadding)
            .padding(horizontal = 8.dp)
            .fillMaxSize()) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(viewModel.table.value.clients.chunked(2)) { pair -> // Chunk the list into pairs of 2
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp), // Adjust bottom padding for spacing
                        horizontalArrangement = Arrangement.spacedBy(8.dp) // Space between cards
                    ) {
                        pair.forEach { client ->
                            // Each card takes half the width
                            ClientCard(
                                clientVM = client,
                                modifier = Modifier.weight(1f),
                                onDeleteClick = {
                                    viewModel.onEvent(ClientEvent.Delete(client))
                                    scope.launch {
                                        snackbarHostState.showSnackbar("Client was deleted successfully")
                                    }
                                },
                                onEditClick = {
                                    navController.navigate(Screen.AddEditCategoryScreen.route + "?categoryId=${client.id}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}