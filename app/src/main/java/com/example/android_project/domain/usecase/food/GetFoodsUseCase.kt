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
        try {
            val foodEntities = foodApiService.getFoods()
            emit(foodEntities.map { food -> food})
        }
        catch (e: Exception) {
            Log.d("ClientListException", e.toString())
            emit(emptyList()) // Emit an empty list on failure
        }
    }

}
