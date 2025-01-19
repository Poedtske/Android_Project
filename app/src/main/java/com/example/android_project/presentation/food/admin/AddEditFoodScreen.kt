package com.example.android_project.presentation.food.admin

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.android_project.R
import com.example.android_project.classes.*
import com.example.android_project.utils.Screen
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditFoodScreen(
    navController: NavHostController,
    viewModel: AddEditFoodViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditFoodEvent.SaveFood)
                },
            ) {
                Icon(imageVector = Icons.Default.Done, contentDescription = "Save Food")
            }
        }
    ) { contentPadding ->

        LaunchedEffect(true) {
            viewModel.eventflow.collectLatest { event ->
                when (event) {
                    AddEditFoodUiEvent.SavedFood -> navController.navigate(Screen.FoodListScreen.route)
                    is AddEditFoodUiEvent.ShowMessage -> {
                        snackbarHostState.showSnackbar(message = event.message)
                    }
                }
            }
        }

        val food = viewModel.foodVM.value
        val categories = viewModel.categories.value

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            // Title
            Text(
                style = MaterialTheme.typography.headlineLarge,
                text = stringResource(R.string.add_food),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )

            // Name Input
            OutlinedTextField(
                value = food.name,
                label = { Text(stringResource(R.string.name)) },
                onValueChange = {
                    viewModel.onEvent(AddEditFoodEvent.EnteredName(it))
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineMedium.copy(color = colorResource(R.color.text)),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Price Input
            OutlinedTextField(
                value = food.price.toString(),
                label = { Text(stringResource(R.string.price)) },
                onValueChange = { input ->
                    val parsedPrice = input.toDoubleOrNull()
                    if (parsedPrice != null) {
                        viewModel.onEvent(AddEditFoodEvent.EnteredPrice(parsedPrice))
                    }
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineMedium.copy(color = colorResource(R.color.text)),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
            Log.d("Available Debug",food.availability.toString())
            // Availability Checkbox
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.available),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.headlineMedium.copy(color = colorResource(R.color.text)),
                )
                Checkbox(
                    checked = food.availability is Available,
                    onCheckedChange = { isChecked ->
                        val newAvailability = if (isChecked) Available else NotAvailable
                        viewModel.onEvent(AddEditFoodEvent.AvailabilityChanged(newAvailability))
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Food Category Dropdown
            var expandedCategory by remember { mutableStateOf(false) }
            var selectedCategory = food.category

            Log.d("CategoryDebug", categories.toString())

            ExposedDropdownMenuBox(
                expanded = expandedCategory,
                onExpandedChange = { expandedCategory = !expandedCategory }
            ) {
                OutlinedTextField(
                    readOnly = true,
                    value = selectedCategory.name,
                    onValueChange = {},
                    label = { Text(stringResource(R.string.category)) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCategory) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expandedCategory,
                    onDismissRequest = { expandedCategory = false }
                ) {
                    categories.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category.name) },
                            onClick = {
                                selectedCategory = category
                                expandedCategory = false
                                viewModel.onEvent(AddEditFoodEvent.CategoryChanged(category))
                            }
                        )
                    }
                }
            }
        }
    }
}
