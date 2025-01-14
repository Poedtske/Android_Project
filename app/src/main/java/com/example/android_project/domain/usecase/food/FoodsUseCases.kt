package com.example.android_project.domain.usecase.food

data class FoodsUseCases (
    val getFoods: GetFoodsUseCase,
    val getFood: GetFoodUseCase,
    val upsertFood: UpsertFoodUseCase,
    val deleteFood: DeleteFoodUseCase
)