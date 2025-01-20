package com.example.android_project.presentation.category.admin

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
import com.example.android_project.utils.Screen
import kotlinx.coroutines.flow.collectLatest


@Composable
fun AddEditCategoryScreen(
    navController: NavHostController,
    viewModel: AddEditCategoryViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditCategoryEvent.SaveCategory)
                },
            ) {
                Icon(imageVector = Icons.Default.Done, contentDescription = "Save Category")
            }
        }
    ) { contentPadding ->

        LaunchedEffect(true) {
            viewModel.eventflow.collectLatest { event ->
                when (event) {
                    AddEditCategoryUiEvent.SavedCategory -> navController.navigate(Screen.CategoriesListScreen.route)
                    is AddEditCategoryUiEvent.ShowMessage -> {
                        snackbarHostState.showSnackbar(message = event.message)
                    }
                }
            }
        }

        val food = viewModel.categoryVM.value

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            // Title
            Text(
                style = MaterialTheme.typography.headlineLarge,
                text = stringResource(R.string.add_category),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )

            // Name Input
            OutlinedTextField(
                value = food.name,
                label = { Text(stringResource(R.string.name)) },
                onValueChange = {
                    viewModel.onEvent(AddEditCategoryEvent.EnteredName(it))
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineMedium.copy(color = colorResource(R.color.text)),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Price Input
            OutlinedTextField(
                value = food.img,
                label = { Text(stringResource(R.string.img)) },
                onValueChange = { input ->
                    val img = input
                    viewModel.onEvent(AddEditCategoryEvent.EnteredImg(img))
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineMedium.copy(color = colorResource(R.color.text)),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
