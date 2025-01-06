package com.example.android_project.classes

import androidx.compose.ui.graphics.Color
import com.example.android_project.classes.ui.theme.AvailableGreen
import com.example.android_project.classes.ui.theme.NotAvailableRed

import kotlin.random.Random

data class FoodVM(
    val id: Int = Random.nextInt(),
    val name: String="",
    val img: String= "placeholder",
    val price: Double=0.0,
    val category: FoodCategory= FoodCategory.GRILL,
    val course: Course = Course.MAIN,
    val availability: FoodAvailability =Available
)

sealed class FoodAvailability(val backgroundColor: Color, val foreGroundColor: Color)
data object Available:FoodAvailability(AvailableGreen, Color.White)
data object NotAvailable:FoodAvailability(NotAvailableRed, Color.Black)