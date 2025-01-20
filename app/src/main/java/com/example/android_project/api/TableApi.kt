package com.example.android_project.api


import com.example.android_project.classes.TableVM
import com.example.android_project.data.model.TableEntity

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TableApiService {
    @GET("secure/restaurant/tables")
    suspend fun getTables(
        @Header("X-API-KEY") apiKey: String = API_KEY
    ): List<TableEntity>

    @GET("secure/restaurant/tables/{id}")
    suspend fun getTableById(
        @Path("id") id: String, // Pass the product ID here
        @Header("X-API-KEY") apiKey: String = API_KEY // Use BuildConfig to access the API key
    ): TableEntity

    @DELETE("secure/restaurant/tables/{id}")
    suspend fun deleteTableById(
        @Path("id") id: String, // Pass the product ID here
        @Header("X-API-KEY") apiKey: String = API_KEY // Use BuildConfig to access the API key
    )

    @PUT("secure/restaurant/tables")
    suspend fun updateTable(
        @Body updatedTable: TableEntity, // Send updated food details as the request body
        @Header("X-API-KEY") apiKey: String = API_KEY,
    )

    @POST("secure/restaurant/tables")
    suspend fun createTable(
        @Body newTable: TableEntity, // Send updated food details as the request body
        @Header("X-API-KEY") apiKey: String = API_KEY,
    )
}