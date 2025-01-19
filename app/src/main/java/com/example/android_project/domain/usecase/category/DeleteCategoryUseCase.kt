package com.example.android_project.domain.usecase.category

import com.example.android_project.api.CategoryApiService
import com.example.android_project.classes.CategoryVM

import com.example.android_project.utils.Exception
import kotlin.jvm.Throws

class DeleteCategoryUseCase(private val categoryApiService: CategoryApiService) {
    @Throws(Exception::class)
    suspend operator fun invoke(category: CategoryVM){
        categoryApiService.deleteCategoryById(category.id.toString())
    }
}