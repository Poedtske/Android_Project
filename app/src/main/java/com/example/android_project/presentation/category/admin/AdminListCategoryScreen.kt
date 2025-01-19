package com.example.android_project.presentation.category.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.android_project.presentation.components.CategoryCard
import com.example.android_project.presentation.components.CategoryEvent


import com.example.android_project.utils.Screen
import kotlinx.coroutines.launch

@Composable
fun AdminListCategoriesScreen(navController: NavController, viewModel: AdminListCategoryViewModel = hiltViewModel()) {

    val snackbarHostState = remember {SnackbarHostState()}
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {SnackbarHost(snackbarHostState)},
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AddEditCategoryScreen.route)
            },
                modifier = Modifier
                    .background(Color.White)) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add a category")
            }
        }
    ) { contentPadding ->
        Column(modifier = Modifier
            .padding(contentPadding)
            .padding(horizontal = 8.dp)
            .fillMaxSize()) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(viewModel.category.value.chunked(2)) { pair -> // Chunk the list into pairs of 2
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp), // Adjust bottom padding for spacing
                        horizontalArrangement = Arrangement.spacedBy(8.dp) // Space between cards
                    ) {
                        pair.forEach { category ->
                            // Each card takes half the width
                            CategoryCard(categorieVM = category, modifier = Modifier.weight(1f).clickable {
                                navController.navigate(Screen.AddEditCategoryScreen.route + "?categoryId=${category.id}")
                            }){
                                viewModel.onEvent(CategoryEvent.Delete(category))
                                scope.launch {
                                    snackbarHostState.showSnackbar("Category was deleted successfully")
                                }

                            }
                        }
                    }
                }
            }
        }
    }

}



