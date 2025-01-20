package com.example.android_project.api

import com.example.android_project.classes.CategoryVM
import com.example.android_project.data.model.CategoryEntity
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CategoryApiService {
    @GET("secure/restaurant/categories")
    suspend fun getCategories(
        @Header("X-API-KEY") apiKey: String = API_KEY
    ): List<CategoryVM>

    @GET("secure/restaurant/categories/{id}")
    suspend fun getCategoryById(
        @Path("id") id: String, // Pass the product ID here
        @Header("X-API-KEY") apiKey: String = API_KEY // Use BuildConfig to access the API key
    ): CategoryVM

    @DELETE("secure/restaurant/categories/{id}")
    suspend fun deleteCategoryById(
        @Path("id") id: String, // Pass the product ID here
        @Header("X-API-KEY") apiKey: String = API_KEY // Use BuildConfig to access the API key
    )

    @PUT("secure/restaurant/categories")
    suspend fun updateCategory(
        @Body updatedCategory: CategoryEntity, // Send updated food details as the request body
        @Header("X-API-KEY") apiKey: String = API_KEY,
    )

    @POST("secure/restaurant/categories")
    suspend fun createCategory(
        @Body newCatefory: CategoryEntity, // Send updated food details as the request body
        @Header("X-API-KEY") apiKey: String = API_KEY,
    )
}