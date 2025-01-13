package com.example.android_project.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.android_project.domain.model.DrinkItem
import com.example.android_project.domain.model.DrinksWithOrder
import kotlinx.coroutines.flow.Flow

@Dao
interface DrinkDao {

    @Query("SELECT * FROM Drinks")
    fun getDrinks():Flow<List<DrinkItem>>

    @Query("SELECT * FROM Drinks WHERE drinkId = :id")
    suspend fun getDrinkItem(id:Int): DrinkItem?

    @Upsert
    suspend fun upsertDrinkItem(clientItem: DrinkItem)

    @Delete
    suspend fun deleteDrinkItem(clientItem: DrinkItem)

    @Transaction
    @Query("SELECT * FROM Drinks")
    fun getDrinksWithOrders(): List<DrinksWithOrder>
}