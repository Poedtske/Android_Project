package com.example.android_project.domain.usecase.food

import com.example.android_project.api.FoodApiService
import com.example.android_project.classes.FoodVM
import com.example.android_project.data.model.FoodEntity

class GetFoodUseCase(private val foodApiService: FoodApiService) {
    suspend operator fun invoke(foodId: Int): FoodVM? {
        return try {
            // Fetch the food item from the API
            val foodEntity:FoodEntity = foodApiService.getFoodById(foodId.toString())
            foodEntity.toVM()
        } catch (e: Exception) {
            // Handle exceptions (e.g., network issues) and return null if something goes wrong
            e.printStackTrace()
            null
        }
    }
}
