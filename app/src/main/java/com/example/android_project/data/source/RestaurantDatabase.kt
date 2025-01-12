package com.example.android_project.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android_project.domain.model.FoodItem

@Database(entities = [FoodItem::class], version = 1)
abstract class RestaurantDatabase:RoomDatabase() {

    abstract val foodDao: FoodDao

    companion object{
        const val DATABASE_NAME = "restaurant.db"
    }
}