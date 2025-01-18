package com.example.android_project.domain.usecase.category

import com.example.android_project.api.CategoryApiService
import com.example.android_project.classes.CategoryVM

class GetCategoryUseCase(private val categoryApiService: CategoryApiService) {
    suspend operator fun invoke(id: Int): CategoryVM?{
        return categoryApiService.getCategoryById(id.toString())
    }
}