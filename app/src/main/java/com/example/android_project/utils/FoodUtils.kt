package com.example.android_project.utils

import com.example.android_project.classes.Available
import com.example.android_project.classes.Course
import com.example.android_project.classes.Food
import com.example.android_project.classes.FoodCategory
import com.example.android_project.classes.NotAvailable
import com.example.android_project.presentation.components.SortByName
import com.example.android_project.presentation.components.SortByPrice
import com.example.android_project.presentation.components.SortOrder
import kotlinx.coroutines.flow.flow

private val foodList: MutableList<Food> = mutableListOf(
    Food(name = "Cordon Bleu", img = "cordon_bleu", category = FoodCategory.GRILL, course = Course.MAIN, price = 1.5, availability = Available),
    Food(name = "Caesar Salad", img = "caesar_salad", category = FoodCategory.SALADS, course = Course.MAIN, price = 1.5, availability = Available),
    Food(name = "Steak", img = "steak", category = FoodCategory.GRILL, course = Course.MAIN, price = 2.5, availability = Available),
    Food(name = "Pasta Alfredo", img = "pasta_alfredo", category = FoodCategory.GRILL, course = Course.MAIN, price = 2.0, availability = Available),
    Food(name = "Chocolate Cake", img = "chocolate_cake", category = FoodCategory.SEAFOOD, course = Course.DESSERT, price = 3.0, availability = NotAvailable),
)

fun getFood(orderBy: SortOrder) = flow {
    emit( when(orderBy){
        SortByName -> foodList.sortedBy { it.name.lowercase() }
        SortByPrice -> foodList.sortedBy { it.price }
    })

}

fun getFoodItem(foodId: Int): Food?{
    return foodList.find { it.id==foodId }
}

fun addOrUpdateFood(food: Food) {

    throw FoodException("Unable to save book")

    val existingBook = foodList.find { it.id == food.id }

    existingBook?.let {
        foodList.remove(it)
    }
    foodList.add(food)

}

fun removeFood(food: Food): List<Food> {
    foodList.remove(food)
    return foodList
}

class FoodException(message: String):Throwable(message)