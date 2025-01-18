package com.example.android_project.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android_project.classes.FoodCategory
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "Food")
data class FoodItem(
    @PrimaryKey(autoGenerate = true)val id: Int?=null,
    val name: String,
    val img: String,
    val price: Double,
    val category: FoodCategory,
    val availability: Boolean
)