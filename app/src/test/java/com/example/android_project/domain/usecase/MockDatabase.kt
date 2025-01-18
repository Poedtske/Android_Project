package com.example.android_project.domain.usecase

import com.example.android_project.data.source.FoodDao
import com.example.android_project.domain.model.FoodItem
import com.example.android_project.domain.model.FoodWithOrders
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockDatabase: FoodDao {

    val foods= mutableListOf<FoodItem>()
    override fun getFood(): Flow<List<FoodItem>> = flow {
        emit(foods)
    }

    override suspend fun getFoodItem(id: Int): FoodItem? {
        return foods.find { it.id==id }
    }

    override suspend fun upsertFoodItem(foodItem: FoodItem) {
        if(foods.contains(foodItem)){
            foods.remove(foodItem)
        }
        foods.add(foodItem)
    }

    override suspend fun deleteFoodItem(foodItem: FoodItem) {
        foods.remove(foodItem)
    }

    override fun getFoodWithOrders(): List<FoodWithOrders> {
        TODO("Not yet implemented")
    }
}