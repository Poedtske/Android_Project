package com.example.android_project.presentation

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.android_project.R
import com.example.android_project.classes.*
import com.example.android_project.utils.Screen
import com.google.android.gms.common.internal.StringResourceValueReader
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditFoodScreen(
    navController: NavHostController,
    viewModel: AddEditFoodViewModel = hiltViewModel()
) {

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {SnackbarHost(snackbarHostState)},
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
                when(event){
                    AddEditFoodUiEvent.SavedFood -> navController.navigate(Screen.FoodListScreen.route)
                    is AddEditFoodUiEvent.ShowMessage ->{
                        snackbarHostState.showSnackbar(message = event.message)
                    }
                }

            }
        }

        val food = viewModel.foodVM.value

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

            ExposedDropdownMenuBox(
                expanded = expandedCategory,
                onExpandedChange = { expandedCategory = !expandedCategory }
            ) {
                Log.d("CategoryDebug",selectedCategory.name)
                OutlinedTextField(
                    readOnly = true,
                    value = stringResource(selectedCategory.displayName),
                    onValueChange = {},
                    label = { Text(stringResource(R.string.category)) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCategory) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expandedCategory,
                    onDismissRequest = { expandedCategory = false }
                ) {
                    FoodCategory.values().forEach { category ->
                        DropdownMenuItem(
                            text = { Text(stringResource(category.displayName)) },
                            onClick = {
                                selectedCategory = category
                                expandedCategory = false
                                viewModel.onEvent(AddEditFoodEvent.CategoryChanged(category))
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Course Dropdown
            var expandedCourse by remember { mutableStateOf(false) }
            var selectedCourse = food.course

            ExposedDropdownMenuBox(
                expanded = expandedCourse,
                onExpandedChange = { expandedCourse = !expandedCourse }
            ) {
                OutlinedTextField(
                    readOnly = true,
                    value = stringResource(id=selectedCourse.displayName),
                    onValueChange = {},
                    label = { Text(stringResource(R.string.course)) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCourse) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expandedCourse,
                    onDismissRequest = { expandedCourse = false }
                ) {
                    Course.values().forEach { course ->
                        DropdownMenuItem(
                            text = { Text(stringResource(id=course.displayName)) },
                            onClick = {
                                selectedCourse = course
                                expandedCourse = false
                                viewModel.onEvent(AddEditFoodEvent.CourseChanged(course))
                            }
                        )
                    }
                }
            }
        }
    }
}
