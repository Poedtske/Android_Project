package com.example.android_project.domain.usecase.category

import com.example.android_project.api.CategoryApiService
import com.example.android_project.api.FoodApiService
import com.example.android_project.classes.CategoryVM


class UpsertCategoryUseCase(private val categoryApiService: CategoryApiService) {
    //@Throws(FoodException::class)
    suspend operator fun invoke(category: CategoryVM){
        /*if(food.name.isEmpty()){
            throw FoodException("Name of food is empty")
        }else if(food.price<=0.0){
            throw FoodException("Price of food is invalid")
        }*/

    }
}