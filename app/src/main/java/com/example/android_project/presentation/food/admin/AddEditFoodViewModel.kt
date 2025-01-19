package com.example.android_project.presentation.food.admin

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_project.classes.CategoryVM
import com.example.android_project.classes.FoodVM
import com.example.android_project.domain.usecase.category.CategoryUseCases
import com.example.android_project.domain.usecase.food.FoodsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditFoodViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val foodsUseCases: FoodsUseCases,
    private val categoryUseCases: CategoryUseCases
) : ViewModel() {

    private val _food = mutableStateOf(FoodVM())
    val foodVM: State<FoodVM> = _food

    private val _categories = mutableStateOf<List<CategoryVM>>(emptyList())
    val categories: State<List<CategoryVM>> = _categories

    private val _eventFlow = MutableSharedFlow<AddEditFoodUiEvent>()
    val eventflow = _eventFlow.asSharedFlow()

    init {
        val foodId = savedStateHandle.get<Int>("foodId") ?: -1
        if (foodId != -1) {
            findFood(foodId)
        }
        fetchCategories()
    }

    // Fetch food by ID
    private fun findFood(foodId: Int) {
        viewModelScope.launch {
            val foodEntity = foodsUseCases.getFood(foodId)
            _food.value = foodEntity!!
        }
    }

    // Fetch categories from use case
    private fun fetchCategories() {
        viewModelScope.launch {
            categoryUseCases.getCategories()
                .catch { e -> Log.e("CategoryError", "Failed to fetch categories", e) } // Log errors
                .collect { fetchedCategories ->
                    _categories.value = fetchedCategories
                }
        }
    }

    // Handle UI events
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

            AddEditFoodEvent.SaveFood -> {
                viewModelScope.launch {
                    if (validateFood()) {
                        foodsUseCases.upsertFood(foodVM.value)
                        _eventFlow.emit(AddEditFoodUiEvent.SavedFood)
                    }
                }
            }

            is AddEditFoodEvent.AvailabilityChanged -> {
                _food.value = _food.value.copy(availability = event.availability)
                Log.d("AvailabilityDebug", "Availability changed to: ${_food.value.availability}")
            }
        }
    }

    // Validate food fields
    private suspend fun validateFood(): Boolean {
        return when {
            foodVM.value.name.isEmpty() -> {
                _eventFlow.emit(AddEditFoodUiEvent.ShowMessage("Name cannot be empty"))
                false
            }

            foodVM.value.price.isNaN() || foodVM.value.price <= 0.0 -> {
                _eventFlow.emit(AddEditFoodUiEvent.ShowMessage("Invalid price"))
                false
            }

            else -> true
        }
    }
}


// UI Events for AddEditFood
sealed interface AddEditFoodUiEvent {
    data class ShowMessage(val message: String) : AddEditFoodUiEvent
    data object SavedFood : AddEditFoodUiEvent
}
