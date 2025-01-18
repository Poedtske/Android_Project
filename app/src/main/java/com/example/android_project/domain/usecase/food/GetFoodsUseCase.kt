package com.example.android_project.domain.usecase.food

import android.util.Log
import com.example.android_project.api.FoodApiService
import com.example.android_project.classes.Availability
import com.example.android_project.classes.CategoryVM
import com.example.android_project.classes.FoodVM
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFoodsUseCase(private val foodApiService: FoodApiService) {
    operator fun invoke(): Flow<List<FoodVM>> = flow {
        try {
            // Fetch data from API
            val foodEntities = foodApiService.getFoods()

            // Emit the sorted list
            emit(foodEntities.map { food->food.toVM() })
        } catch (e: Exception) {
            Log.d("FoodListException", e.toString())
            emit(emptyList())
        }
    }
}
