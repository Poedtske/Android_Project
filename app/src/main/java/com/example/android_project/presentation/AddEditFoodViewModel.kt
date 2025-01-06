package com.example.android_project.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.android_project.classes.Food
import com.example.android_project.utils.addOrUpdateFood


class AddEditFoodViewModel() : ViewModel() {

    private val _food = mutableStateOf(Food())
    val food: State<Food> = _food

    fun onEvent(event: AddEditFoodEvent) {
        when (event) {
            is AddEditFoodEvent.EnteredName -> {
                _food.value = _food.value.copy(name = event.name)
            }

            is AddEditFoodEvent.EnteredPrice -> {
                _food.value = _food.value.copy(price = event.price)
            }

            is AddEditFoodEvent.CategoryChanged -> {
                _food.value = _food.value.copy(category = event.category)
            }

            is AddEditFoodEvent.CourseChanged -> {
                _food.value = _food.value.copy(course = event.course)
            }

            AddEditFoodEvent.SaveFood -> {
                addOrUpdateFood(food.value)
            }

            is AddEditFoodEvent.AvailabilityChanged -> {
                _food.value = _food.value.copy(availability = event.availability)
            }
        }
    }
}

