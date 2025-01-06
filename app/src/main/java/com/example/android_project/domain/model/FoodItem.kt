package com.example.android_project.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android_project.classes.Available
import com.example.android_project.classes.Course
import com.example.android_project.classes.FoodAvailability
import com.example.android_project.classes.FoodCategory
import kotlin.random.Random

@Entity(tableName = "Food")
data class FoodItem(
    @PrimaryKey(autoGenerate = true)val id: Int?=null,
    val name: String,
    val img: String,
    val price: Double,
    val category: Int,
    val course: Int,
    val availability: Int
)