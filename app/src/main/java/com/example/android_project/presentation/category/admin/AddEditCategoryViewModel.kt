package com.example.android_project.presentation.category.admin

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_project.classes.CategoryVM
import com.example.android_project.domain.usecase.category.CategoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditCategoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val categoryUseCases: CategoryUseCases
) : ViewModel() {

    private val _category = mutableStateOf(CategoryVM())
    val categoryVM: State<CategoryVM> = _category



    private val _eventFlow = MutableSharedFlow<AddEditCategoryUiEvent>()
    val eventflow = _eventFlow.asSharedFlow()

    init {
        val categoryId = savedStateHandle.get<Int>("categoryId") ?: -1
        if (categoryId != -1) {
            findCategory(categoryId)
        }
    }

    // Fetch food by ID
    private fun findCategory(categoryId: Int) {
        viewModelScope.launch {
            val categoryEntity = categoryUseCases.getCategory(categoryId)
            _category.value = categoryEntity!!
        }
    }

    // Handle UI events
    fun onEvent(event: AddEditCategoryEvent) {
        when (event) {
            is AddEditCategoryEvent.EnteredName -> {
                _category.value = _category.value.copy(name = event.name)
            }



            AddEditCategoryEvent.SaveCategory -> {
                viewModelScope.launch {
                    try {
                        categoryUseCases.upsertCategory(categoryVM.value)
                        _eventFlow.emit(AddEditCategoryUiEvent.SavedCategory)
                    }catch(e: Exception) {
                        _eventFlow.emit(AddEditCategoryUiEvent.ShowMessage(e.message?:"An error occurred"))
                    }

                }
            }

            is AddEditCategoryEvent.EnteredImg -> {
                _category.value=_category.value.copy(img=event.img)
            }
        }
    }
}


// UI Events for AddEditFood
sealed interface AddEditCategoryUiEvent {
    data class ShowMessage(val message: String) : AddEditCategoryUiEvent
    data object SavedCategory : AddEditCategoryUiEvent
}
