package com.example.android_project.di

import android.app.Application
import androidx.room.Room
import com.example.android_project.data.source.RestaurantDatabase
import com.example.android_project.domain.usecase.DeleteFoodUseCase
import com.example.android_project.domain.usecase.FoodsUseCases
import com.example.android_project.domain.usecase.GetFoodUseCase
import com.example.android_project.domain.usecase.GetFoodsUseCase
import com.example.android_project.domain.usecase.UpsertFoodUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
//tells daggerhilt its a module it can use
@Module
@InstallIn(SingletonComponent::class) //tells how many times it can use this
object AppModule{
    //tells hilt it provides a class
    @Provides
    @Singleton
    fun provideRestaurantDatabase(context: Application): RestaurantDatabase{
        return Room.databaseBuilder(
            context,
            RestaurantDatabase::class.java,
            RestaurantDatabase.DATABASE_NAME
        ).build()
    }
    @Provides
    @Singleton
    fun provideFoodUseCases(db:RestaurantDatabase):FoodsUseCases{
        return FoodsUseCases(
            getFoods = GetFoodsUseCase(db.foodDao),
            getFood= GetFoodUseCase(db.foodDao),
            upsertFood = UpsertFoodUseCase(db.foodDao),
            deleteFood = DeleteFoodUseCase(db.foodDao)
        )
    }
}