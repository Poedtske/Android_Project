package com.example.android_project.domain.usecase.client

data class ClientsUseCases (
    val getClients: GetClientsUseCase,
    val getClient: GetClientUseCase,
    val upsertClient: UpsertClientUseCase,
    val deleteClient: DeleteClientUseCase
)