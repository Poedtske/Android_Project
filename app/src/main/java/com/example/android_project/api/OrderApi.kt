package com.example.android_project.api


import com.example.android_project.data.model.OrderEntity

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface OrderApiService {
    @GET("secure/restaurant/orders")
    suspend fun getOrders(
        @Header("X-API-KEY") apiKey: String = API_KEY
    ): List<OrderEntity>

    @GET("secure/restaurant/orders/{id}")
    suspend fun getOrderById(
        @Path("id") id: String, // Pass the product ID here
        @Header("X-API-KEY") apiKey: String = API_KEY // Use BuildConfig to access the API key
    ): OrderEntity

    @DELETE("secure/restaurant/orders/{id}")
    suspend fun deleteOrderById(
        @Path("id") id: String, // Pass the product ID here
        @Header("X-API-KEY") apiKey: String = API_KEY // Use BuildConfig to access the API key
    )

    @PUT("secure/restaurant/orders")
    suspend fun updateOrder(
        @Body updatedOrder: OrderEntity, // Send updated food details as the request body
        @Header("X-API-KEY") apiKey: String = API_KEY,
    )

    @POST("secure/restaurant/orders")
    suspend fun createOrder(
        @Body newOrder: OrderEntity, // Send updated food details as the request body
        @Header("X-API-KEY") apiKey: String = API_KEY,
    )
}