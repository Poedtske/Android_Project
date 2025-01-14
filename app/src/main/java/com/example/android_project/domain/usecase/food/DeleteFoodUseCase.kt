package com.example.android_project.domain.usecase.food

import com.example.android_project.data.source.FoodDao
import com.example.android_project.domain.model.FoodItem
import com.example.android_project.utils.FoodException
import kotlin.jvm.Throws

class DeleteFoodUseCase(private val foodDao: FoodDao) {
    @Throws(FoodException::class)
    suspend operator fun invoke(food: FoodItem){
        foodDao.deleteFoodItem(food)
    }
}