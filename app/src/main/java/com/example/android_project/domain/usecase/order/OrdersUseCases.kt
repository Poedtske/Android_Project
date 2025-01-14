package com.example.android_project.domain.usecase.order

data class OrdersUseCases (
    val getOrders: GetOrdersUseCase,
    val getOrder: GetOrderUseCase,
    val upsertOrder: UpsertOrderUseCase,
    val deleteOrder: DeleteOrderUseCase,
    val getOrdersWithDrinksUseCase: GetOrdersWithDrinksUseCase,
    val getOrdersWithFoodUseCase: GetOrdersWithFoodUseCase
    )