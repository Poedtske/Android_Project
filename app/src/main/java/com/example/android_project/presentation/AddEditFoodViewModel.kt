package com.example.android_project.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_project.classes.Food
import com.example.android_project.presentation.components.SortByName
import com.example.android_project.utils.FoodException
import com.example.android_project.utils.addOrUpdateFood
import com.example.android_project.utils.getFood
import com.example.android_project.utils.getFoodItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


class AddEditFoodViewModel(foodId: Int=-1) : ViewModel() {

    private val _food = mutableStateOf(Food())
    val food: State<Food> = _food

    private val _eventFlow= MutableSharedFlow<AddEditFoodUiEvent>()
    val eventflow= _eventFlow.asSharedFlow()

    private fun findFood(foodId: Int){
        _food.value= getFoodItem(foodId) ?: Food()
    }

    init {
        findFood(foodId)
    }

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
                viewModelScope.launch {
                    try{
                        addOrUpdateFood(food.value)
                        _eventFlow.emit(AddEditFoodUiEvent.SavedBook)
                    }catch (e: FoodException){
                        _eventFlow.emit(AddEditFoodUiEvent.ShowMessage(e.message!!))
                    }
                }

            }

            is AddEditFoodEvent.AvailabilityChanged -> {
                _food.value = _food.value.copy(availability = event.availability)
            }
        }
    }
}

sealed interface AddEditFoodUiEvent {
    data class ShowMessage(val message: String) : AddEditFoodUiEvent
    data object SavedBook:AddEditFoodUiEvent
}

