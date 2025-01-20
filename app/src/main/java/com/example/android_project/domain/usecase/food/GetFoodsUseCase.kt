package com.example.android_project.domain.usecase.food

import android.util.Log
import com.example.android_project.api.FoodApiService
import com.example.android_project.classes.Availability
import com.example.android_project.classes.CategoryVM
import com.example.android_project.classes.FoodVM
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetFoodsUseCase(private val foodApiService: FoodApiService) {
    operator fun invoke(): Flow<List<FoodVM>> = flow {
        // Fetch data from the API and emit the result
        val foodEntities = foodApiService.getFoods()
        emit(foodEntities.map { food -> food.toVM() })
    }.catch { e ->
        // Catch any exception that occurs in the flow and log it
        Log.d("FoodListException", e.toString())
        // Emit an empty list as a fallback
        emit(emptyList())
    }
}
