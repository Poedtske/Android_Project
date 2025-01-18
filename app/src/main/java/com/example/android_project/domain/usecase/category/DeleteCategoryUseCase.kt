package com.example.android_project.domain.usecase.category

import com.example.android_project.api.CategoryApiService
import com.example.android_project.api.FoodApiService
import com.example.android_project.classes.CategoryVM
import com.example.android_project.classes.ClientVM

import com.example.android_project.utils.FoodException
import kotlin.jvm.Throws

class DeleteCategoryUseCase(private val categoryApiService: CategoryApiService) {
    @Throws(FoodException::class)
    suspend operator fun invoke(category: CategoryVM){

    }
}