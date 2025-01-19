package com.example.android_project.presentation.category.admin

sealed interface AddEditCategoryEvent {
    data class EnteredName(val name: String) : AddEditCategoryEvent
    data class EnteredImg(val img: String) : AddEditCategoryEvent
    data object SaveCategory: AddEditCategoryEvent

}