package com.example.android_project.domain.usecase.food

import com.example.android_project.data.source.FoodDao
import com.example.android_project.domain.model.FoodItem
import com.example.android_project.utils.FoodException
import kotlin.jvm.Throws

class UpsertFoodUseCase(private val foodDao: FoodDao) {
    @Throws(FoodException::class)
    suspend operator fun invoke(food:FoodItem){
        if(food.name.isEmpty()){
            throw FoodException("Name of food is empty")
        }else if(food.price<=0.0){
            throw FoodException("Price of food is invalid")
        }
        foodDao.upsertFoodItem(food)
    }
}