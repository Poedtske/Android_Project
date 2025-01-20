package com.example.android_project.di

import com.example.android_project.api.CategoryApiService
import com.example.android_project.api.ClientApiService
import com.example.android_project.api.FoodApiService
import com.example.android_project.api.OrderApiService
import com.example.android_project.api.RetrofitInstance
import com.example.android_project.api.TableApiService
import com.example.android_project.domain.usecase.category.CategoryUseCases
import com.example.android_project.domain.usecase.category.DeleteCategoryUseCase
import com.example.android_project.domain.usecase.category.GetCategoriesUseCase
import com.example.android_project.domain.usecase.category.GetCategoryUseCase
import com.example.android_project.domain.usecase.category.UpsertCategoryUseCase
import com.example.android_project.domain.usecase.client.ClientUseCases
import com.example.android_project.domain.usecase.client.DeleteClientUseCase
import com.example.android_project.domain.usecase.client.GetClientUseCase
import com.example.android_project.domain.usecase.client.GetClientsUseCase
import com.example.android_project.domain.usecase.client.UpsertClientUseCase

import com.example.android_project.domain.usecase.food.DeleteFoodUseCase
import com.example.android_project.domain.usecase.food.FoodsUseCases
import com.example.android_project.domain.usecase.food.GetFoodUseCase
import com.example.android_project.domain.usecase.food.GetFoodsUseCase
import com.example.android_project.domain.usecase.food.UpsertFoodUseCase
import com.example.android_project.domain.usecase.order.DeleteOrderUseCase
import com.example.android_project.domain.usecase.order.GetOrderUseCase
import com.example.android_project.domain.usecase.order.GetOrdersUseCase
import com.example.android_project.domain.usecase.order.OrderUseCases
import com.example.android_project.domain.usecase.order.UpsertOrderUseCase
import com.example.android_project.domain.usecase.table.DeleteTableUseCase
import com.example.android_project.domain.usecase.table.GetTableUseCase
import com.example.android_project.domain.usecase.table.GetTablesUseCase
import com.example.android_project.domain.usecase.table.TableUseCases
import com.example.android_project.domain.usecase.table.UpsertTableUseCase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
//tells daggerhilt its a module it can use
@Module
@InstallIn(SingletonComponent::class) //tells how many times it can use this
object AppModule{


    @Provides
    @Singleton
    fun provideFoodApiService(): FoodApiService {
        return RetrofitInstance.foodApiService
    }

    @Provides
    @Singleton
    fun provideCategoryApiService(): CategoryApiService {
        return RetrofitInstance.categoryApiService
    }

    @Provides
    @Singleton
    fun provideFoodUseCases(foodApiService: FoodApiService): FoodsUseCases {
        return FoodsUseCases(
            getFoods = GetFoodsUseCase(foodApiService),
            getFood= GetFoodUseCase(foodApiService),
            upsertFood = UpsertFoodUseCase(foodApiService),
            deleteFood = DeleteFoodUseCase(foodApiService)
        )
    }

    @Provides
    @Singleton
    fun provideCategoryUseCases(categoryApiService: CategoryApiService): CategoryUseCases {
        return CategoryUseCases(
            getCategories = GetCategoriesUseCase(categoryApiService),
            getCategory = GetCategoryUseCase(categoryApiService),
            upsertCategory = UpsertCategoryUseCase(categoryApiService),
            deleteCategory = DeleteCategoryUseCase(categoryApiService)
        )
    }

    @Provides
    @Singleton
    fun provideClientUseCases(clientApiService: ClientApiService): ClientUseCases {
        return ClientUseCases(
            getClients = GetClientsUseCase(clientApiService),
            getClient = GetClientUseCase(clientApiService),
            upsertClient = UpsertClientUseCase(clientApiService),
            deleteClient = DeleteClientUseCase(clientApiService)
        )
    }

    @Provides
    @Singleton
    fun provideTableUseCases(tableApiService: TableApiService): TableUseCases {
        return TableUseCases(
            getTables = GetTablesUseCase(tableApiService),
            getTable = GetTableUseCase(tableApiService),
            upsertTable = UpsertTableUseCase(tableApiService),
            deleteTable = DeleteTableUseCase(tableApiService)
        )
    }

    @Provides
    @Singleton
    fun provideOrderUseCases(orderApiService: OrderApiService): OrderUseCases {
        return OrderUseCases(
            getOrders = GetOrdersUseCase(orderApiService),
            getOrder = GetOrderUseCase(orderApiService),
            upsertOrder = UpsertOrderUseCase(orderApiService),
            deleteOrder = DeleteOrderUseCase(orderApiService)
        )
    }
}