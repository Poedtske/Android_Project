package com.example.android_project.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_project.classes.FoodVM
import com.example.android_project.data.source.FoodDao
import com.example.android_project.utils.FoodException
import com.example.android_project.utils.addOrUpdateFood
import com.example.android_project.utils.getFoodItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


class AddEditFoodViewModel(foodId: Int = -1, val foodDao: FoodDao) : ViewModel() {

    private val _food = mutableStateOf(FoodVM())
    val foodVM: State<FoodVM> = _food

    private val _eventFlow= MutableSharedFlow<AddEditFoodUiEvent>()
    val eventflow= _eventFlow.asSharedFlow()

    private fun findFood(foodId: Int){
        viewModelScope.launch {
            val foodEntity=foodDao.getFoodItem(foodId)
            _food.value= foodEntity?.let { FoodVM.fromEntity(it)}?: FoodVM()
        }
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
                    if(foodVM.value.name.isEmpty()||foodVM.value.price.isNaN()){
                        _eventFlow.emit(AddEditFoodUiEvent.ShowMessage("Unable to save Food, name or price is empty"))
                    } else if(foodVM.value.price==0.0||foodVM.value.price<0.0){
                        _eventFlow.emit(AddEditFoodUiEvent.ShowMessage("Unable to save Food, price is invalid"))
                    }
                    else{
                        val entity = foodVM.value.toEntity()
                        foodDao.upsertFoodItem(entity)
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

