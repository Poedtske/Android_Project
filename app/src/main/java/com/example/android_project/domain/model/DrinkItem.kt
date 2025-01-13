package com.example.android_project.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android_project.classes.DrinkCategory
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "Drinks")
data class DrinkItem(
    @PrimaryKey(autoGenerate = true)val drinkId: Int?=null,
    val name: String,
    val img: String,
    val price: Double,
    val category: DrinkCategory,
    val availability: Boolean
)