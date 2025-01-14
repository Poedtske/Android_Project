package com.example.android_project.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_project.classes.FoodVM
import com.example.android_project.domain.usecase.food.FoodsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditFoodViewModel @Inject constructor
    (savedStateHandle: SavedStateHandle, private val foodsUseCases: FoodsUseCases) : ViewModel() {

    private val _food = mutableStateOf(FoodVM())
    val foodVM: State<FoodVM> = _food

    private val _eventFlow= MutableSharedFlow<AddEditFoodUiEvent>()
    val eventflow= _eventFlow.asSharedFlow()

    private fun findFood(foodId: Int){
        viewModelScope.launch {
            val foodEntity=foodsUseCases.getFood(foodId)
            _food.value= foodEntity?.let { FoodVM.fromEntity(it)}?: FoodVM()
        }
    }

    init {
        val foodId= savedStateHandle.get<Int>("foodId")?:-1
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
                    if(foodVM.value.name.isEmpty()||foodVM.value.price.isNaN()){
                        _eventFlow.emit(AddEditFoodUiEvent.ShowMessage("Unable to save Food, name or price is empty"))
                    } else if(foodVM.value.price==0.0||foodVM.value.price<0.0){
                        _eventFlow.emit(AddEditFoodUiEvent.ShowMessage("Unable to save Food, price is invalid"))
                    }
                    else{
                        val entity = foodVM.value.toEntity()
                        foodsUseCases.upsertFood(entity)
                        _eventFlow.emit(AddEditFoodUiEvent.SavedBook)
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

