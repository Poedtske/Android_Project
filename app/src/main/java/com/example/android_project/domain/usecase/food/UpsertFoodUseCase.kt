package com.example.android_project.domain.usecase.food

import com.example.android_project.api.FoodApiService
import com.example.android_project.classes.CategoryVM
import com.example.android_project.classes.FoodVM
import com.example.android_project.data.model.FoodEntity
import com.example.android_project.utils.Exception
import kotlin.jvm.Throws

class UpsertFoodUseCase(private val foodApiService: FoodApiService) {
    @Throws(Exception::class)
    suspend operator fun invoke(food:FoodVM){
        if(food.name.isEmpty()){
            throw Exception("Name of food is empty")
        }else if(food.price<=0.0){
            throw Exception("Price of food is invalid")
        }else if (food.category.id==-1 ){
            throw Exception("Category is invalid")
        }
        val foodEntity: FoodEntity=food.toEntity()
        if(food.id==-1){
            foodApiService.createFood(foodEntity)
        }else{
            foodApiService.updateFood(foodEntity)
        }
    }
}