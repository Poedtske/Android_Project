package com.example.android_project.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_project.classes.FoodVM
import com.example.android_project.domain.usecase.food.FoodsUseCases
import com.example.android_project.presentation.components.FoodEvent
import com.example.android_project.presentation.components.SortByName
import com.example.android_project.presentation.components.SortOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminListProductViewModel @Inject constructor
    (private val foodsUseCases: FoodsUseCases): ViewModel() {
    private val _food: MutableState<List<FoodVM>> = mutableStateOf(emptyList())
    var food: State<List<FoodVM>> =_food

    private var _sortOrder: MutableState<SortOrder> = mutableStateOf(SortByName)
    var sortOrder: State<SortOrder> = _sortOrder

    var job: Job?=null

    init {
        loadFood(sortOrder.value)
    }

    private fun loadFood(sortOrder: SortOrder) {
        job?.cancel()

        job=foodsUseCases.getFoods(sortOrder).onEach {
            food->_food.value=food.map { FoodVM.fromEntity(it) }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: FoodEvent){
        when(event){
            is FoodEvent.Delete -> {
                deleteFood(event.foodVM)
                loadFood(sortOrder.value)
            }
            is FoodEvent.Order -> {
                _sortOrder.value = event.order
                loadFood(event.order)
            }
        }
    }

    private fun deleteFood(foodVM: FoodVM) {
        viewModelScope.launch {
            foodsUseCases.deleteFood(foodVM.toEntity())
        }

    }
}