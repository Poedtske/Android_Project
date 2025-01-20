package com.example.android_project.domain.usecase.client

import com.example.android_project.api.ClientApiService
import com.example.android_project.classes.ClientVM

class GetClientUseCase(private val clientApiService: ClientApiService) {
    suspend operator fun invoke(id: Int): ClientVM?{
        return clientApiService.getClientById(id.toString()).toVM()
    }
}