package com.example.android_project.domain.usecase

import com.example.android_project.data.source.FoodDao
import com.example.android_project.domain.model.FoodItem

class GetFoodUseCase(private val foodDao: FoodDao) {
    suspend operator fun invoke(foodId: Int):FoodItem?{
        return foodDao.getFoodItem(foodId)
    }
}