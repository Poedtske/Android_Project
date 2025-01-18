package com.example.android_project.presentation

import com.example.android_project.classes.Course
import com.example.android_project.classes.Availability
import com.example.android_project.classes.CategoryVM
import com.example.android_project.classes.FoodCategory

sealed interface AddEditFoodEvent {
    data class EnteredName(val name: String) : AddEditFoodEvent
    data class EnteredPrice(val price: Double): AddEditFoodEvent
    data object SaveFood: AddEditFoodEvent
    data class CategoryChanged(val category: CategoryVM): AddEditFoodEvent
    data class AvailabilityChanged(val availability: Availability): AddEditFoodEvent
}