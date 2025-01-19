package com.example.android_project.presentation.food.admin

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_project.classes.CategoryVM
import com.example.android_project.classes.FoodVM
import com.example.android_project.domain.usecase.category.CategoryUseCases
import com.example.android_project.domain.usecase.food.FoodsUseCases
import com.example.android_project.presentation.components.CategoryEvent
import com.example.android_project.presentation.components.FoodEvent
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



    var job: Job?=null

    init {
        loadFood()
    }

    private fun loadFood() {
        job?.cancel()

        job = foodsUseCases.getFoods().onEach { foods ->
            _food.value = foods // Update the state here
        }.launchIn(viewModelScope)
    }


    fun onEvent(event: FoodEvent){
        when(event){
            is FoodEvent.Delete -> {
                deleteFood(event.foodVM)
                loadFood()
            }
        }
    }

    private fun deleteFood(foodVM: FoodVM) {
        viewModelScope.launch {
            foodsUseCases.deleteFood(foodVM)
        }

    }
}