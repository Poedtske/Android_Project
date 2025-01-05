package com.example.android_project.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.android_project.classes.foods
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import com.example.android_project.presentation.components.FoodCard

@Composable
fun ListProductsScreen(innerPadding: PaddingValues) {
    var localFood by remember { mutableStateOf(foods) }

    // Sort foods alphabetically by food name
    val sortedFoods = localFood.sortedBy { it.name }

    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        items(sortedFoods.chunked(2)) { foodPair -> // Chunk the list into pairs of 2
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp), // Adjust bottom padding for spacing
                horizontalArrangement = Arrangement.spacedBy(8.dp) // Space between cards
            ) {
                foodPair.forEach { food ->
                    // Each card takes half the width
                    FoodCard(food = food, modifier = Modifier.weight(1f)){
                        println("Deleting food")
                        localFood= localFood.filter { it!=food }.toMutableList()
                    }
                }
            }
        }
    }
}



