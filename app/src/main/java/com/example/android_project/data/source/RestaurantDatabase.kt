package com.example.android_project.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android_project.domain.model.ClientItem
import com.example.android_project.domain.model.DrinkItem
import com.example.android_project.domain.model.FoodItem
import com.example.android_project.domain.model.OrderItem
import com.example.android_project.domain.model.TableItem

@Database(
    entities = [FoodItem::class, DrinkItem::class, OrderItem::class,TableItem::class, ClientItem::class],
    version = 2)
abstract class RestaurantDatabase:RoomDatabase() {

    abstract val foodDao: FoodDao
    abstract val drinkDao: DrinkDao
    abstract val clientDao: ClientDao
    abstract val tableDao: TableDao
    abstract val orderDao: OrderDao

    companion object{
        const val DATABASE_NAME = "restaurant.db"
    }
}