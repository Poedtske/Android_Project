package com.example.android_project.domain.usecase.client

data class ClientUseCases (
    val getClients: GetClientsUseCase,
    val getClient: GetClientUseCase,
    val upsertClient: UpsertClientUseCase,
    val deleteClient: DeleteClientUseCase
)