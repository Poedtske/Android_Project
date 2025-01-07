package com.example.android_project.api

import com.example.android_project.domain.model.FoodItem
import retrofit2.http.GET


interface FoodApi {

    @GET()
    suspend fun getFood():List<FoodItem>
}