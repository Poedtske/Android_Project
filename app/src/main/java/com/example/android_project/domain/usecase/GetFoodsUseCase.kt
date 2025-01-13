package com.example.android_project.domain.usecase

import com.example.android_project.classes.FoodVM
import com.example.android_project.data.source.FoodDao
import com.example.android_project.domain.model.FoodItem
import com.example.android_project.presentation.components.SortByName
import com.example.android_project.presentation.components.SortByPrice
import com.example.android_project.presentation.components.SortOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFoodsUseCase(private val foodDao: FoodDao) {
    operator fun invoke (orderBy: SortOrder): Flow<List<FoodItem>>{
        return foodDao.getFood()
            .map{ foods->
                when(orderBy){
                    SortByName -> foods.sortedBy { it.name }
                    SortByPrice -> foods.sortedBy { it.price }
                }
            }
    }
}