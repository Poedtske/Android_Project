package com.example.android_project.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.android_project.classes.Food
import com.example.android_project.presentation.components.FoodEvent
import com.example.android_project.presentation.components.SortByName
import com.example.android_project.presentation.components.SortOrder
import com.example.android_project.utils.getFood
import com.example.android_project.utils.removeFood

class ListFoodViewModel: ViewModel() {
    private val _foods: MutableState<List<Food>> = mutableStateOf(emptyList())
    var foods: State<List<Food>> =_foods

    private var _sortOrder: MutableState<SortOrder> = mutableStateOf(SortByName)
    var sortOrder: State<SortOrder> = _sortOrder


    init {
        _foods.value=loadFood(sortOrder.value)
    }

    private fun loadFood(sortOrder: SortOrder): List<Food> {
        return getFood(sortOrder)
    }

    fun onEvent(event: FoodEvent){
        when(event){
            is FoodEvent.Delete -> {
                _foods.value=deleteFood(event.food)
                _foods.value=loadFood(sortOrder.value)
            }
            is FoodEvent.Order -> {
                _sortOrder.value = event.order
                _foods.value = loadFood(event.order)
            }
        }
    }

    private fun deleteFood(food: Food): List<Food> {
        return removeFood(food)
    }
}