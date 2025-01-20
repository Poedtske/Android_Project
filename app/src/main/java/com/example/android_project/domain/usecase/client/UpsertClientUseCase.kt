package com.example.android_project.domain.usecase.client

import com.example.android_project.api.ClientApiService
import com.example.android_project.classes.ClientVM
import com.example.android_project.utils.Exception


class UpsertClientUseCase(private val clientApiService: ClientApiService) {
    @Throws(Exception::class)
    suspend operator fun invoke(client: ClientVM){
        if(client.table.id==-1){
            throw Exception("No Table")
        }

        if(client.id==-1){
            clientApiService.createClient(client.toEntity())
        }else{
            clientApiService.updateClient(client.toEntity())
        }

    }
}