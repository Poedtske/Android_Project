package com.example.android_project.domain.usecase.order

data class OrderUseCases (
    val getOrders: GetOrdersUseCase,
    val getOrder: GetOrderUseCase,
    val upsertOrder: UpsertOrderUseCase,
    val deleteOrder: DeleteOrderUseCase
)