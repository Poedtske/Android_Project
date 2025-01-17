package com.example.android_project.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.android_project.domain.model.FoodItem
import com.example.android_project.domain.model.FoodWithOrders
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Query("SELECT * FROM Food")
    fun getFood():Flow<List<FoodItem>>

    @Query("SELECT * FROM Food WHERE foodId = :id")
    suspend fun getFoodItem(id:Int):FoodItem?

    @Upsert
    suspend fun upsertFoodItem(foodItem: FoodItem)

    @Delete
    suspend fun deleteFoodItem(foodItem: FoodItem)

    @Transaction
    @Query("SELECT * FROM Food")
    fun getFoodWithOrders(): List<FoodWithOrders>
}