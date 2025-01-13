package com.example.android_project.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import com.example.android_project.classes.Course
import com.example.android_project.classes.FoodCategory
import kotlinx.serialization.Serializable
import javax.annotation.Nonnull

@Serializable
@Entity(tableName = "Food")
data class FoodItem(
    @Nonnull
    @PrimaryKey val id: String = "",  // Default values for all fields
    val name: String = "",
    val img: String = "",
    val price: Double = 0.0,
    val category: FoodCategory = FoodCategory.UNKNOWN, // Provide a default for enums
    val course: Course = Course.UNKNOWN, // Provide a default for enums
    val availability: Boolean = true
)
