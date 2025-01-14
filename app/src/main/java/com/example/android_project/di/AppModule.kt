package com.example.android_project.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.android_project.data.source.RestaurantDatabase
import com.example.android_project.domain.usecase.food.DeleteFoodUseCase
import com.example.android_project.domain.usecase.food.FoodsUseCases
import com.example.android_project.domain.usecase.food.GetFoodUseCase
import com.example.android_project.domain.usecase.food.GetFoodsUseCase
import com.example.android_project.domain.usecase.food.UpsertFoodUseCase
import com.example.android_project.domain.usecase.table.GetTableUseCase
import com.example.android_project.domain.usecase.table.GetTablesUseCase
import com.example.android_project.domain.usecase.table.GetTablesWithClientsUseCase
import com.example.android_project.domain.usecase.table.TablesUseCases
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
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    Log.d("DatabaseDebug", "onCreate called for database.")

                    // Prepopulate 31 restaurant tables
                    try {
                        for (tableNumber in 1..31) {
                            db.execSQL("INSERT INTO Tables (tableId) VALUES ($tableNumber)")
                        }
                        Log.d("DatabaseDebug", "Successfully inserted 31 tables.")
                    } catch (e: Exception) {
                        Log.e("DatabaseDebug", "Error inserting tables: ${e.message}")
                    }
                }
            })
            .build()
    }
    @Provides
    @Singleton
    fun provideFoodUseCases(db:RestaurantDatabase): FoodsUseCases {
        return FoodsUseCases(
            getFoods = GetFoodsUseCase(db.foodDao),
            getFood= GetFoodUseCase(db.foodDao),
            upsertFood = UpsertFoodUseCase(db.foodDao),
            deleteFood = DeleteFoodUseCase(db.foodDao)
        )
    }
    @Provides
    @Singleton
    fun provideTableUseCases(db:RestaurantDatabase): TablesUseCases {
        return TablesUseCases(
            getTables = GetTablesUseCase(db.tableDao),
            getTable = GetTableUseCase(db.tableDao),
            getTablesWithClients = GetTablesWithClientsUseCase(db.tableDao)
        )
    }
}