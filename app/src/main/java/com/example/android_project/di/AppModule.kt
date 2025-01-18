package com.example.android_project.di

import com.example.android_project.api.CategoryApiService
import com.example.android_project.api.FoodApiService
import com.example.android_project.api.RetrofitInstance
import com.example.android_project.domain.usecase.category.CategoryUseCases
import com.example.android_project.domain.usecase.category.DeleteCategoryUseCase
import com.example.android_project.domain.usecase.category.GetCategoriesUseCase
import com.example.android_project.domain.usecase.category.GetCategoryUseCase
import com.example.android_project.domain.usecase.category.UpsertCategoryUseCase

import com.example.android_project.domain.usecase.food.DeleteFoodUseCase
import com.example.android_project.domain.usecase.food.FoodsUseCases
import com.example.android_project.domain.usecase.food.GetFoodUseCase
import com.example.android_project.domain.usecase.food.GetFoodsUseCase
import com.example.android_project.domain.usecase.food.UpsertFoodUseCase

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
}