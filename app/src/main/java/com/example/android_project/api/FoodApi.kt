package com.example.android_project.api

import com.example.android_project.classes.FoodVM
import com.example.android_project.data.model.FoodEntity
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface FoodApiService {
    @GET("secure/restaurant/products")
    suspend fun getFoods(
        @Header("X-API-KEY") apiKey: String = API_KEY
    ): List<FoodVM>

    @GET("secure/restaurant/products/{id}")
    suspend fun getFoodById(
        @Path("id") id: String, // Pass the product ID here
        @Header("X-API-KEY") apiKey: String = API_KEY // Use BuildConfig to access the API key
    ): FoodVM

    @DELETE("secure/restaurant/products/{id}")
    suspend fun deleteFoodById(
        @Path("id") id: String, // Pass the product ID here
        @Header("X-API-KEY") apiKey: String = API_KEY // Use BuildConfig to access the API key
    )

    @PUT("secure/restaurant/products")
    suspend fun updateFood(
        @Body updatedFood: FoodEntity, // Send updated food details as the request body
        @Header("X-API-KEY") apiKey: String = API_KEY,
    )

    @POST("secure/restaurant/products")
    suspend fun createFood(
        @Body newCatefory: FoodEntity, // Send updated food details as the request body
        @Header("X-API-KEY") apiKey: String = API_KEY,
    )
}