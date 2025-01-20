package com.example.android_project.domain.usecase.client

import com.example.android_project.api.ClientApiService
import com.example.android_project.classes.ClientVM

import com.example.android_project.utils.Exception
import kotlin.jvm.Throws

class DeleteClientUseCase(private val clientApiService: ClientApiService) {
    @Throws(Exception::class)
    suspend operator fun invoke(client: ClientVM){
        if (client.id!=-1 && !client.paid && client.orders.isNotEmpty()){
            throw Exception("Cannot delete client if they have orders and haven't paid")
        }

        clientApiService.deleteClientById(client.id.toString())
    }
}