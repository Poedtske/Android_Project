package com.example.android_project.presentation

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
import androidx.navigation.NavController

import com.example.android_project.presentation.components.FoodCard
import com.example.android_project.presentation.components.FoodEvent
import com.example.android_project.presentation.components.SortOptions
import com.example.android_project.utils.Screen
import kotlinx.coroutines.launch

@Composable
fun AdminListProductsScreen(foodViewModel: AdminListProductViewModel, navController: NavController) {

    val snackbarHostState = remember {SnackbarHostState()}
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {SnackbarHost(snackbarHostState)},
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AddEditFoodScreen.route)
            },
                modifier = Modifier
                    .background(Color.White)) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add a food item")
            }
        }
    ) { contentPadding ->
        Column(modifier = Modifier
            .padding(contentPadding)
            .padding(horizontal = 8.dp)
            .fillMaxSize()) {

            SortOptions(foodOrder = foodViewModel.sortOrder.value, onSortOrderChange = {order ->
                foodViewModel.onEvent(FoodEvent.Order(order))
            })
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(foodViewModel.food.value.chunked(2)) { foodPair -> // Chunk the list into pairs of 2
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp), // Adjust bottom padding for spacing
                        horizontalArrangement = Arrangement.spacedBy(8.dp) // Space between cards
                    ) {
                        foodPair.forEach { food ->
                            println("foodId: "+food.id)
                            // Each card takes half the width
                            FoodCard(foodVM = food, modifier = Modifier.weight(1f).clickable {
                                navController.navigate(Screen.AddEditFoodScreen.route + "?foodId=${food.id}")
                            }){
                                foodViewModel.onEvent(FoodEvent.Delete(food))
                                scope.launch {
                                    snackbarHostState.showSnackbar("Food item was deleted successfully")
                                }

                            }
                        }
                    }
                }
            }
        }
    }

}



