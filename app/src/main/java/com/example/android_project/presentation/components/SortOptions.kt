package com.example.android_project.presentation.components

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