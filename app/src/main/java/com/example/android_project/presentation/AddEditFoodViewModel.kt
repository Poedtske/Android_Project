package com.example.android_project.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_project.classes.FoodVM
import com.example.android_project.utils.FoodException
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AddEditFoodViewModel(foodId: String? = null, private val database: FirebaseDatabase) : ViewModel() {

    private val _food = mutableStateOf(FoodVM())
    val foodVM: State<FoodVM> = _food

    private val _eventFlow = MutableSharedFlow<AddEditFoodUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        foodId?.let {
            findFood(it)
        }
    }

    private fun findFood(foodId: String) {
        viewModelScope.launch {
            try {
                val foodReference = database.getReference("food").child(foodId)
                val snapshot = foodReference.get().await()
                val foodItem = snapshot.getValue(FoodVM::class.java)
                _food.value = foodItem ?: FoodVM()
            } catch (e: Exception) {
                _eventFlow.emit(AddEditFoodUiEvent.ShowMessage("Error loading food: ${e.message}"))
            }
        }
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
                    val food = foodVM.value
                    if (food.name.isEmpty() || food.price.isNaN()) {
                        _eventFlow.emit(AddEditFoodUiEvent.ShowMessage("Unable to save Food, name or price is empty"))
                        return@launch
                    }
                    if (food.price <= 0.0) {
                        _eventFlow.emit(AddEditFoodUiEvent.ShowMessage("Unable to save Food, price is invalid"))
                        return@launch
                    }

                    try {
                        val foodReference = database.getReference("food")
                        var foodId:String=""
                        if (!"${food.id}".isEmpty()){
                            foodId=foodReference.push().key.toString()
                        }

                        foodReference.child(foodId).setValue(food.copy(id = foodId)).await()

                        _eventFlow.emit(AddEditFoodUiEvent.SavedBook)
                    } catch (e: Exception) {
                        _eventFlow.emit(AddEditFoodUiEvent.ShowMessage("Error saving food: ${e.message}"))
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
    data object SavedBook : AddEditFoodUiEvent
}
