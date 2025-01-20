package com.example.android_project.api

import com.example.android_project.data.model.ClientEntity

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ClientApiService {
    @GET("secure/restaurant/clients")
    suspend fun getClients(
        @Header("X-API-KEY") apiKey: String = API_KEY
    ): List<ClientEntity>

    @GET("secure/restaurant/clients/{id}")
    suspend fun getClientById(
        @Path("id") id: String, // Pass the product ID here
        @Header("X-API-KEY") apiKey: String = API_KEY // Use BuildConfig to access the API key
    ): ClientEntity

    @DELETE("secure/restaurant/clients/{id}")
    suspend fun deleteClientById(
        @Path("id") id: String, // Pass the product ID here
        @Header("X-API-KEY") apiKey: String = API_KEY // Use BuildConfig to access the API key
    )

    @PUT("secure/restaurant/clients")
    suspend fun updateClient(
        @Body updatedClient: ClientEntity, // Send updated food details as the request body
        @Header("X-API-KEY") apiKey: String = API_KEY,
    )

    @POST("secure/restaurant/clients")
    suspend fun createClient(
        @Body newClient: ClientEntity, // Send updated food details as the request body
        @Header("X-API-KEY") apiKey: String = API_KEY,
    )

    @PUT("secure/restaurant/clients/{clientId}/tables/{tableId}")
    suspend fun switchTables(
        @Path("clientId") clientId: String, // Pass the product ID here
        @Path("tableId") tableId: String, // Pass the product ID here
        @Header("X-API-KEY") apiKey: String = API_KEY,
    )
}