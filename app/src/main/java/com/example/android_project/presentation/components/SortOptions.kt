package com.example.android_project.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.android_project.R
import com.example.android_project.classes.CategoryVM
import com.example.android_project.classes.ClientVM
import com.example.android_project.classes.FoodVM



data class NotesState(
    val foodVM: List<FoodVM> = emptyList(),
)

sealed class FoodEvent {
    data class Delete(val foodVM: FoodVM):FoodEvent()
}

sealed class CategoryEvent {
    data class Delete(val categoryVM: CategoryVM):CategoryEvent()
}

sealed class ClientEvent {
    data class Delete(val clientVM: ClientVM):ClientEvent()
}