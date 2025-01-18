package com.example.android_project.domain.usecase.food

import com.example.android_project.api.FoodApiService
import com.example.android_project.classes.FoodVM
import com.example.android_project.utils.FoodException
import kotlin.jvm.Throws

class DeleteFoodUseCase(private val foodApiService: FoodApiService) {
    @Throws(FoodException::class)
    suspend operator fun invoke(food: FoodVM){
        foodApiService.deleteFoodById(food.id.toString())
    }
}