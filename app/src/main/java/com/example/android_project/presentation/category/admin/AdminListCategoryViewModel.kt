package com.example.android_project.presentation.category.admin

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
class AdminListCategoryViewModel @Inject constructor
    (private val categoryUseCases: CategoryUseCases): ViewModel() {

    private val _category: MutableState<List<CategoryVM>> = mutableStateOf(emptyList())
    var category: State<List<CategoryVM>> =_category



    var job: Job?=null

    init {
        loadCategories()
    }

    private fun loadCategories() {
        job?.cancel()

        job = categoryUseCases.getCategories().onEach { category ->
            _category.value = category // Update the state here
        }.launchIn(viewModelScope)
    }


    fun onEvent(event: CategoryEvent){
        when(event){
            is CategoryEvent.Delete -> {
                deleteFood(event.categoryVM)
                loadCategories()
            }
        }
    }

    private fun deleteFood(categoryVM: CategoryVM) {
        viewModelScope.launch {
            categoryUseCases.deleteCategory(categoryVM)
        }

    }
}