package com.example.android_project.domain.usecase.category

import android.util.Log
import com.example.android_project.api.CategoryApiService
import com.example.android_project.classes.CategoryVM
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCategoriesUseCase(private val apiService: CategoryApiService) {
    operator fun invoke(): Flow<List<CategoryVM>> = flow {
        try {
            val categories = apiService.getCategories()
            emit(categories) // Emit the fetched categories
        } catch (e: Exception) {
            Log.d("CategoryListException", e.toString())
            emit(emptyList()) // Emit an empty list on failure
        }
    }
}