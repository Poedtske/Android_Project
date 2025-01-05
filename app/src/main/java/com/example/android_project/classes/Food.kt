package com.example.android_project.classes

import androidx.compose.ui.graphics.Color
import com.example.android_project.classes.ui.theme.AvailableGreen
import com.example.android_project.classes.ui.theme.NotAvailableRed

data class Food(
    override var name: String,
    override var img: String,
    override var price: Double,
    var category: FoodCategory,            // Category of the product
    var course: Course,
    val availability: FoodAvailability
) : Product()

sealed class FoodAvailability(val backgroundColor: Color, val foreGroundColor: Color)
data object Available:FoodAvailability(AvailableGreen, Color.White)
data object NotAvailable:FoodAvailability(NotAvailableRed, Color.Black)

val foods = mutableListOf(
    Food(name = "Cordon Bleu", img = "cordon_bleu", category = FoodCategory.GRILL, course = Course.MAIN, price = 1.5, availability = Available),
    Food(name = "Caesar Salad", img = "caesar_salad", category = FoodCategory.SALADS, course = Course.MAIN, price = 1.5, availability = Available),
    Food(name = "Steak", img = "steak", category = FoodCategory.GRILL, course = Course.MAIN, price = 2.5, availability = Available),
    Food(name = "Pasta Alfredo", img = "pasta_alfredo", category = FoodCategory.GRILL, course = Course.MAIN, price = 2.0, availability = Available),
    Food(name = "Chocolate Cake", img = "chocolate_cake", category = FoodCategory.SEAFOOD, course = Course.DESSERT, price = 3.0, availability = NotAvailable),
)
