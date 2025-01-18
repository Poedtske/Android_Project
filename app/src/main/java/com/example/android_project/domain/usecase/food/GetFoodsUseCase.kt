package com.example.android_project.domain.usecase.food

import android.util.Log
import com.example.android_project.api.FoodApiService
import com.example.android_project.data.source.FoodDao
import com.example.android_project.domain.model.FoodItem
import com.example.android_project.presentation.components.SortByName
import com.example.android_project.presentation.components.SortByPrice
import com.example.android_project.presentation.components.SortOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFoodsUseCase(private val foodApiService: FoodApiService) {
    operator fun invoke(orderBy: SortOrder): Flow<List<FoodItem>> = flow {
        try {
            // Fetch data from API
            val foods = foodApiService.getFoods()

            // Sort the data
            val sortedFoods = when (orderBy) {
                SortByName -> foods.sortedBy { it.name }
                SortByPrice -> foods.sortedBy { it.price }
            }

            // Emit the sorted list
            emit(sortedFoods)
        } catch (e: Exception) {
            Log.d("FoodListException",e.toString())
            emit(emptyList())
        }
    }
}