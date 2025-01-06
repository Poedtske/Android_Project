package com.example.android_project.presentation

import com.example.android_project.classes.Course
import com.example.android_project.classes.Availability
import com.example.android_project.classes.FoodCategory

sealed interface AddEditFoodEvent {
    data class EnteredName(val name: String) : AddEditFoodEvent
    data class EnteredPrice(val price: Double): AddEditFoodEvent
    data object SaveFood: AddEditFoodEvent
    data class CategoryChanged(val category: FoodCategory): AddEditFoodEvent
    data class CourseChanged(val course: Course): AddEditFoodEvent
    data class AvailabilityChanged(val availability: Availability): AddEditFoodEvent
}