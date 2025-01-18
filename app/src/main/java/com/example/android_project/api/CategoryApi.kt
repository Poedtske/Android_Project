package com.example.android_project.api

import com.example.android_project.classes.CategoryVM
import retrofit2.http.GET
import retrofit2.http.Header
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

}