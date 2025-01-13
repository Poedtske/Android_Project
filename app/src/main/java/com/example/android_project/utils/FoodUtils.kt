package com.example.android_project.utils

import com.example.android_project.classes.Available
import com.example.android_project.classes.Course
import com.example.android_project.classes.FoodVM
import com.example.android_project.classes.FoodCategory
import com.example.android_project.classes.NotAvailable
import com.example.android_project.presentation.components.SortByName
import com.example.android_project.presentation.components.SortByPrice
import com.example.android_project.presentation.components.SortOrder
import kotlinx.coroutines.flow.flow

private val foodVMLists: MutableList<FoodVM> = mutableListOf(
    FoodVM(name = "Cordon Bleu", img = "cordon_bleu", category = FoodCategory.GRILL, course = Course.MAIN, price = 1.5, availability = Available),
    FoodVM(name = "Caesar Salad", img = "caesar_salad", category = FoodCategory.SALADS, course = Course.MAIN, price = 1.5, availability = Available),
    FoodVM(name = "Steak", img = "steak", category = FoodCategory.GRILL, course = Course.MAIN, price = 2.5, availability = Available),
    FoodVM(name = "Pasta Alfredo", img = "pasta_alfredo", category = FoodCategory.GRILL, course = Course.MAIN, price = 2.0, availability = Available),
    FoodVM(name = "Chocolate Cake", img = "chocolate_cake", category = FoodCategory.SEAFOOD, course = Course.DESSERT, price = 3.0, availability = NotAvailable),
)

fun getFood(orderBy: SortOrder) = flow {
    emit( when(orderBy){
        SortByName -> foodVMLists.sortedBy { it.name.lowercase() }
        SortByPrice -> foodVMLists.sortedBy { it.price }
    })

}

fun getFoodItem(foodId: String): FoodVM?{
    return foodVMLists.find { it.id==foodId }
}

fun addOrUpdateFood(foodVM: FoodVM) {

    throw FoodException("Unable to save book")

    val existingBook = foodVMLists.find { it.id == foodVM.id }

    existingBook?.let {
        foodVMLists.remove(it)
    }
    foodVMLists.add(foodVM)

}

fun removeFood(foodVM: FoodVM): List<FoodVM> {
    foodVMLists.remove(foodVM)
    return foodVMLists
}

class FoodException(message: String):Throwable(message)